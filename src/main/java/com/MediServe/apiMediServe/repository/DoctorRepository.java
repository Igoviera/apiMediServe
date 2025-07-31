package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.enums.DoctorStatus;
import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatus(DoctorStatus status, Pageable pageable);
}

