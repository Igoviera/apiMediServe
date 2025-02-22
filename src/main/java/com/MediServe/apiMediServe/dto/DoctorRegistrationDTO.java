package com.MediServe.apiMediServe.dto;

import java.math.BigDecimal;
import java.util.List;

public record DoctorRegistrationDTO(
        String username,
        String password,
        String name,
        String imgUrl,
        String crm,
        String cpf,
        String phone,
        BigDecimal queryValue,
        AddressDTO address,
        List<Long> specialties,
        List<DoctorScheduleDTO> doctorSchedules,
        Long clinicId
) {}
