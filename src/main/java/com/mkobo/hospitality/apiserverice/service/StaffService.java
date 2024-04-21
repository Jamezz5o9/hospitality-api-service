package com.mkobo.hospitality.apiserverice.service;

import com.mkobo.hospitality.apiserverice.dto.request.CreateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.request.UpdateStaffRequest;
import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.model.Staff;

public interface StaffService {

    ApiResponse<Staff> addStaffMember(CreateStaffRequest request);
    ApiResponse<Staff> updateStaffMember(Long id, UpdateStaffRequest updateRequest);
    boolean isValidUUID(String uuid);
}
