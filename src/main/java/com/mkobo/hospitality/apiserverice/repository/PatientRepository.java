package com.mkobo.hospitality.apiserverice.repository;


import com.mkobo.hospitality.apiserverice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByAgeLessThanEqual(int age);

    List<Patient> findByLastVisitDateBetween(LocalDate startDate, LocalDate endDate);
}

