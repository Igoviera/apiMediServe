package com.MediServe.apiMediServe.dto.doctor;

import com.MediServe.apiMediServe.enums.DoctorStatus;

import java.math.BigDecimal;
import java.util.List;

public record DoctorResponseDTO(
        Long id,
        String name,
        String imgUrl,
        String crm,
        BigDecimal queryValue,
        List<String> specialties,
        Long clinicId,
        Long userId,
        DoctorStatus status
) {}
