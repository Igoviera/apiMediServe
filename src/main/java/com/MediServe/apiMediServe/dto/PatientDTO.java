package com.MediServe.apiMediServe.dto;

import java.time.LocalDate;

public record PatientDTO(
        Long id,
        String nome,
        String cpf,
        String sex,
        String phone,
        LocalDate birthDate
) {}
