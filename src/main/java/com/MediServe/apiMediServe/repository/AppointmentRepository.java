package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;



public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentDateTime(Doctor doctor, LocalDateTime date);
}
