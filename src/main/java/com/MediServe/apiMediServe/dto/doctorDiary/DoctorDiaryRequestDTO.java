package com.MediServe.apiMediServe.dto.doctorDiary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record DoctorDiaryRequestDTO(
        @NotBlank(message = "O dia da semana é obrigatório.")
        String dayOfWeek,

        @NotNull(message = "Horário de início é obrigatório.")
        LocalTime startTime,

        @NotNull(message = "Horário de término é obrigatório.")
        LocalTime endTime,

        @NotNull(message = "Duração é obrigatória.")
        int durationMinutes,

        @NotNull(message = "O id do médico é obrigatorio.")
        Long doctorId
) {
}
