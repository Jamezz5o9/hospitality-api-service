package com.mkobo.hospitality.apiserverice.service.impl;

import com.mkobo.hospitality.apiserverice.dto.response.ApiResponse;
import com.mkobo.hospitality.apiserverice.model.Patient;
import com.mkobo.hospitality.apiserverice.repository.PatientRepository;
import com.mkobo.hospitality.apiserverice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public ApiResponse<List<Patient>> getPatientsUpToTwoYearsOld() {
        List<Patient> patients = patientRepository.findByAgeLessThanEqual(2);
        return new ApiResponse<>(true, "Successfully retrieved young patients", patients);
    }
    @Override
    public ApiResponse<String> downloadPatientProfile(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            return new ApiResponse<>(false, "Patient not found", null);
        }
        String csvBuilder = "ID,Name,Age,Last Visit Date\n" +
                patient.get().getId() +
                ',' +
                patient.get().getName() +
                ',' +
                patient.get().getAge() +
                ',' +
                patient.get().getLastVisitDate();
        return new ApiResponse<>(true, "Successfully generated CSV", csvBuilder);
    }
    @Override
    public void deletePatientsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Patient> patients = patientRepository.findByLastVisitDateBetween(startDate, endDate);
        patientRepository.deleteAll(patients);
    }
}
