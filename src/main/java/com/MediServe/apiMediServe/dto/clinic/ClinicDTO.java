package com.MediServe.apiMediServe.dto.clinic;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ClinicDTO(
        Long id,
        @NotEmpty(message = "O nome é obrigatório")
        String name,
        @NotEmpty(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,
        @NotEmpty(message = "O telefone é obrigatório")
        String phone,
        String imgURL
){}

