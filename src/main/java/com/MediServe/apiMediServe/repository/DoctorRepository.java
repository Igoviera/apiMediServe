package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
