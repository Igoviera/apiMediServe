package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.MediServe.apiMediServe.enums.SchedulingStatus;
import com.MediServe.apiMediServe.model.Scheduling;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulingDTO (
        Long id,
        DayOfTheWeek day,
        LocalTime consultationTime,
        SchedulingStatus schedulingStatus,
        Long patientId,
        Long doctorId,
        Long clinicId
) {}
