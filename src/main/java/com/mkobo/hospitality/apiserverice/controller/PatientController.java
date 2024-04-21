package com.mkobo.hospitality.apiserverice.controller;

import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.model.Patient;
import com.mkobo.hospitality.apiserverice.service.PatientService;
import com.mkobo.hospitality.apiserverice.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @GetMapping("/young-patients")
    public ResponseEntity<ApiResponse<List<Patient>>> getYoungPatients() {
        ApiResponse<List<Patient>> response = patientService.getPatientsUpToTwoYearsOld();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download-profile/{id}")
    public void downloadPatientProfile(@PathVariable Long id,
                                       HttpServletResponse response) throws IOException {
        ApiResponse<String> apiResponse = patientService.downloadPatientProfile(id);
        if (!apiResponse.isSuccess()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"patient_" + id + ".csv\"");
        response.getWriter().write(apiResponse.getData());
    }

    @DeleteMapping("/delete-range")
    public ResponseEntity<ApiResponse<String>> deletePatientsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        patientService.deletePatientsByDateRange(startDate, endDate);
        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Successfully deleted patients within the date range");
        response.setData(null);
        return ResponseEntity.ok(response);
    }
}

