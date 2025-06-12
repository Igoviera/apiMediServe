package com.MediServe.apiMediServe.dto.patient;

import com.MediServe.apiMediServe.dto.address.AddressDTO;
import com.MediServe.apiMediServe.dto.user.UserResponseDTO;

public record PatientResponseDTO(
        Long id,
        String name,
        String cpf,
        String phone,
        AddressDTO address,
        UserResponseDTO user
) {}
