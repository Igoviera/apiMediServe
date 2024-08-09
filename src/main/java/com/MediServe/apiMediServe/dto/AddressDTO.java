package com.MediServe.apiMediServe.dto;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record AddressDTO(
        @NotEmpty(message = "O CEP é obrigatório")
        String cep,
        @NotEmpty(message = "O nome da rua é obrigatório")
        String logradouro,
        @NotEmpty(message = "O Numero é obrigatório")
        String locationNumber,
        @NotEmpty(message = "O bairro é obrigatório")
        String neighborhood,
        @NotEmpty(message = "A cidade é obrigatório")
        String city,
        @NotEmpty(message = "O estado é obrigatório")
        @Length(max = 2, message = "O estado não pode ter mais de 2 caracteres")
        String state
) {}
