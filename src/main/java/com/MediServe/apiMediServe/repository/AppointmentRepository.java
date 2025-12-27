package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorAndDate(Doctor doctor, LocalDateTime date);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByDoctorIdAndDateBetween(
            Long doctorId,
            LocalDateTime start,
            LocalDateTime end
    );

    long countByStatusAppointment(StatusAppointment statusAppointment);
}
