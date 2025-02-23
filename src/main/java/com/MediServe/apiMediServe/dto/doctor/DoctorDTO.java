package com.MediServe.apiMediServe.dto.doctor;

import com.MediServe.apiMediServe.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record DoctorDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatorio")
        @Size(min = 2, max = 100)
        String name,

        String imgUrl,

        @NotBlank(message = "O CRM é obrigatório")
        @Pattern(regexp = "\\d{6}", message = "O CRM deve conter 6 dígitos")
        String crm,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        String description,

        @NotNull(message = "O valor da consulta é obrigatório")
        @Positive(message = "O valor da consulta deve ser positivo")
        BigDecimal queryValue,

        @Valid
        AddressDTO address,

        @NotEmpty(message = "O médico deve ter pelo menos uma especialidade")
        List<Long> specialtyIds,

        @NotNull(message = "O ID da clínica é obrigatório")
        Long clinicId,

        Long userId,

        boolean status
) {}
