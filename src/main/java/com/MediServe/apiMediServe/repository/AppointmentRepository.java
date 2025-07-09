package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorAndDayOfWeekAndTime(Doctor doctor, DayOfWeek dayOfWeek, LocalTime time);
    List<Appointment> findByDoctor(Doctor doctor);

}
