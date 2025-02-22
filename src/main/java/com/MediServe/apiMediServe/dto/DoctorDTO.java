package com.MediServe.apiMediServe.dto;

import java.math.BigDecimal;
import java.util.List;

public record DoctorDTO(
        Long id,
        String name,
        String imgUrl,
        String crm,
        String cpf,
        String phone,
        BigDecimal queryValue,
        AddressDTO address,
        List<Long> specialtyIds,
        List<DoctorScheduleDTO> doctorSchedules,
        Long clinicId,
        Long userId,
        boolean status
) {}
