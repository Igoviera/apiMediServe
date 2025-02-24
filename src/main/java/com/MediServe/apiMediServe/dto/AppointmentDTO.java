package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.MediServe.apiMediServe.enums.SchedulingStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentDTO(

        Long id,

        @NotNull(message = "A data e hora do agendamento são obrigatórias")
        LocalDateTime appointmentDateTime,

        @NotNull(message = "O status do agendamento é obrigatório")
        SchedulingStatus schedulingStatus,

        @NotNull(message = "O paciente é obrigatório")
        Long patientId,

        @NotNull(message = "O médico é obrigatório")
        Long doctorId,

        @NotNull(message = "A clínica é obrigatória")
        Long clinicId
) {}
