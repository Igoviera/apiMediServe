package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.MediServe.apiMediServe.enums.SchedulingStatus;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentDTO(
        Long id,
        LocalDateTime appointmentDateTime,
        SchedulingStatus schedulingStatus,
        Long patientId,
        Long doctorId,
        Long clinicId
) {}
