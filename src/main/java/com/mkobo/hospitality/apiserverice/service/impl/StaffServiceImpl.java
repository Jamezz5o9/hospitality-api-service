package com.mkobo.hospitality.apiserverice.service.impl;

import com.mkobo.hospitality.apiserverice.dto.request.CreateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.request.UpdateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.exception.NotFoundException;
import com.mkobo.hospitality.apiserverice.model.Staff;
import com.mkobo.hospitality.apiserverice.repository.StaffRepository;
import com.mkobo.hospitality.apiserverice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public ApiResponse<Staff> addStaffMember(CreateStaffRequest createRequest) {
        Staff staff = new Staff();
        staff.setName(createRequest.getName());
        staff.setUuid(UUID.randomUUID().toString());
        Staff savedStaff = staffRepository.save(staff);
        return new ApiResponse<>(true, "Staff member added successfully.", savedStaff);
    }

    @Override
    public ApiResponse<Staff> updateStaffMember(Long id, UpdateStaffRequest updateRequest) {
        return staffRepository.findById(id).map(staff -> {
            staff.setName(updateRequest.getName());
            Staff savedStaff = staffRepository.save(staff);
            return new ApiResponse<>(true, "Staff member updated successfully.", savedStaff);
        }).orElseThrow(() -> new NotFoundException("Staff with ID: " + id + " not found."));
    }

    @Override
    public boolean isValidUUID(String uuid) {
        Optional<Staff> staff = staffRepository.findByUuid(uuid);
        return staff.isPresent();
    }
}
