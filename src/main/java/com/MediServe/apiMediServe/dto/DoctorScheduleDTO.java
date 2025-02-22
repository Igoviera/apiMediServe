package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;

import java.time.LocalTime;

public record DoctorScheduleDTO(
        Long id,
        DayOfTheWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Long doctorId
) {}
