package com.mkobo.hospitality.apiserverice.repository;

import com.mkobo.hospitality.apiserverice.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUuid(String uuid);
}

