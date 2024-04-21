package com.mkobo.hospitality.apiserverice.service;

import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.model.Patient;

import java.time.LocalDate;
import java.util.List;

public interface PatientService {

    ApiResponse<List<Patient>> getPatientsUpToTwoYearsOld();

    ApiResponse<String> downloadPatientProfile(Long id);

    void deletePatientsByDateRange(LocalDate startDate, LocalDate endDate);
}
