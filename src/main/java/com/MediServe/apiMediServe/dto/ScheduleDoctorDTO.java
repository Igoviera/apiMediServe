package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalTime;

public record ScheduleDoctorDTO(
        Long id,
        @NotEmpty(message = "O dia é obrigatório")
        DayOfTheWeek dayOfWeek,
        @NotEmpty(message = "A hora de inicio é obrigatória")
        LocalTime startTime,
        @NotEmpty(message = "A hora de encerramento é obrigatória")
        LocalTime endTime,
        Long doctorId
) {}
