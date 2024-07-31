package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
