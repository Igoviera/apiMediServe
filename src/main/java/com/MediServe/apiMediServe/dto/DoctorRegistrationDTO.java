package com.MediServe.apiMediServe.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

public record DoctorRegistrationDTO(
        @NotEmpty(message = "O username é obrigatório")
        String username,
        @NotEmpty(message = "A senha é obrigatória")
        String password,
        @NotEmpty(message = "O nome é obrigatório")
        String name,
        String imgUrl,
        @NotEmpty(message = "O CRM é obrigatório")
        String crm,
        @NotEmpty(message = "O CPF é obrigatório")
        String cpf,
        @NotEmpty(message = "O telefone é obrigatório")
        String phone,
        String description,
        BigDecimal queryValue,
        @Valid
        AddressDTO address,
        List<Long> specialties,
        Long clinicId
) {}
