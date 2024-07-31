package com.MediServe.apiMediServe.dto;

public record AddressDTO(
        String cep,
        String logradouro,
        String locationNumber
) {
}
