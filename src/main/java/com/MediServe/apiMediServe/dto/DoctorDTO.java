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
        AddressDTO addressDTO,
        List<SpecialtyDTO> specialties,
        List<OpeningHoursDTO> openingHours,
        ClinicDTO clinic,
        UserDTO user,
        boolean status
) {}
