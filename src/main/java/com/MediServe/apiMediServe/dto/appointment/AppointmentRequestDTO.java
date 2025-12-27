package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequestDTO(
        @NotNull(message = "O ID da clinica é obrigatório.")
        Long clinicId,

        @NotNull(message = "O ID do médico é obrigatório.")
        Long doctorId,

        @NotNull(message = "O ID do paciente é obrigatório.")
        Long patientId,

        @NotNull(message = "A data é obrigatoria")
        LocalDateTime date
) {
}
