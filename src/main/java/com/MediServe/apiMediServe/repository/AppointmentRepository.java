package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentDateTime(Long doctorId, LocalDateTime date);
}
