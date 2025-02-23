package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.dto.address.AddressDTO;

import java.time.LocalDate;

public record PatientRegistrationDTO(
        String username,
        String password,
        String name,
        String cpf,
        String sex,
        LocalDate birthDate,
        String imgUrl,
        String phone,
        AddressDTO address
){}
