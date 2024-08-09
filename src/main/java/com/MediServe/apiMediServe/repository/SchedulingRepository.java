package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
}
