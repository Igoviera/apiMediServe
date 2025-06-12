package com.MediServe.apiMediServe.dto.doctor;

import com.MediServe.apiMediServe.dto.address.AddressDTO;
import com.MediServe.apiMediServe.dto.user.UserResponseDTO;
import com.MediServe.apiMediServe.enums.DoctorStatus;

import java.math.BigDecimal;
import java.util.List;

public record DoctorResponseDTO(
        Long id,
        String name,
        String imgUrl,
        String crm,
        String cpf,
        String phone,
        String description,
        BigDecimal queryValue,
        AddressDTO address,
        List<Long> specialties,
        DoctorStatus status,
        Long clinicId,
        UserResponseDTO user
) {}
