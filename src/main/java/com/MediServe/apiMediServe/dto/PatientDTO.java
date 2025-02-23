package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.dto.address.AddressDTO;

import java.time.LocalDate;

public record PatientDTO(
        Long id,
        String nome,
        String cpf,
        String sex,
        String phone,
        LocalDate birthDate,
        AddressDTO address,
        Long userId
) {}
