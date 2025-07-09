package com.MediServe.apiMediServe.repository;

import com.MediServe.apiMediServe.model.DoctorDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorDiaryRepository extends JpaRepository<DoctorDiary, Long> {
    List<DoctorDiary> findByDoctorId(Long doctorId);
    //List<DoctorDiary> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    //List<DoctorDiary> findByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);
    Optional<DoctorDiary> findByDoctorIdAndDayOfWeek(Long doctorId, DayOfWeek dayOfWeek);


}
