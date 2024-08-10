package com.MediServe.apiMediServe.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

public record DoctorDTO(
        Long id,
        String name,
        String imgUrl,
        String crm,
        String cpf,
        String phone,
        String description,
        BigDecimal queryValue,
        AddressDTO address,
        List<Long> specialtyIds,
        List<OpeningHoursDTO> openingHours,
        Long clinicId,
        Long userId,
        boolean status
) {}
