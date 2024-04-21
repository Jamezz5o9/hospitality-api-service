package com.mkobo.hospitality.apiserverice;

import com.mkobo.hospitality.apiserverice.controller.StaffController;
import com.mkobo.hospitality.apiserverice.dto.request.CreateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.model.Staff;
import com.mkobo.hospitality.apiserverice.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
class HospitalityApiServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StaffService staffService;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@Test
	@WithMockUser
	public void testAddStaffMember() throws Exception {
		CreateStaffRequest createRequest = new CreateStaffRequest();
		createRequest.setName("John Doe");
		ApiResponse<Staff> apiResponse = new ApiResponse<>(true, "Staff member added successfully.", new Staff(1L, "John Doe", "123-uuid", LocalDate.now()));

		when(staffService.addStaffMember(any(CreateStaffRequest.class))).thenReturn(apiResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/staff/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"John Doe\" }")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Staff member added successfully."))
				.andExpect(jsonPath("$.data.uuid").value("123-uuid"));
	}

	@Test
	@WithMockUser
	public void testAddStaffMemberInvalidData() throws Exception {

		ApiResponse<Staff> apiResponse = new ApiResponse<>(false, "Validation failed.", null);

		when(staffService.addStaffMember(any(CreateStaffRequest.class))).thenReturn(apiResponse);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/staff/add")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"\" }")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andExpect(status().isBadRequest());
	}
}
