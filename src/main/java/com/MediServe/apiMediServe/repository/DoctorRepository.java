package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.DoctorDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
