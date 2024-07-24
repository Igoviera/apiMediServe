package com.MediServe.apiMediServe.dto.clinic;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RequestClinicDTO(
        @NotEmpty(message = "O nome é obrigatório")
        String name,
        @NotEmpty(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,
        String phone,
        String imgURL
){}

