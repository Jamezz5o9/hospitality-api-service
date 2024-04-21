package com.mkobo.hospitality.apiserverice.controller;

import com.mkobo.hospitality.apiserverice.dto.request.CreateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.request.UpdateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/staff")
public class StaffController {


    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<?>> addStaffMember(@Valid @RequestBody CreateStaffRequest createRequest) {
        return ResponseEntity.ok(staffService.addStaffMember(createRequest));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse<?>> updateStaffMember(@PathVariable("id") Long id, @Valid @RequestBody UpdateStaffRequest updateRequest) {
        return ResponseEntity.ok(staffService.updateStaffMember(id, updateRequest));
    }

}

