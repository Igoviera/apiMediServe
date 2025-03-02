package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(

        Long id,

        @NotNull(message = "O ID da clinica é obrigatório.")
        Long clinicId,

        @NotNull(message = "O ID do médico é obrigatório.")
        Long doctorId,

        @NotNull(message = "O ID do paciente é obrigatório.")
        Long pacientId,

        @NotNull(message = "A data da consulta é obrigatória.")
        @FutureOrPresent(message = "A data da consulta deve estar no presente ou no futuro.")
        LocalDateTime data,

        @NotNull(message = "O status da consulta é obrigatório.")
        StatusAppointment status
) {}
