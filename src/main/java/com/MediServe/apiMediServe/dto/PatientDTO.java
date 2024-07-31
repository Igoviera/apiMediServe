package com.MediServe.apiMediServe.dto;

import java.time.LocalDate;

public record PatientDTO(
        Long id,
        String username,
        String password,
        String nome,
        String cpf,
        String sex,
        String phone,
        LocalDate birthDate
) {}
