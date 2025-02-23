package com.MediServe.apiMediServe.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record AddressDTO(
        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678")
        String cep,
        @NotBlank(message = "O nome da rua é obrigatório")
        String logradouro,
        @NotBlank(message = "O Numero é obrigatório")
        String locationNumber,
        @NotBlank(message = "O bairro é obrigatório")
        String neighborhood,
        @NotBlank(message = "A cidade é obrigatório")
        String city,
        @NotBlank(message = "O estado é obrigatório")
        @Length(max = 2, message = "O estado não pode ter mais de 2 caracteres")
        String uf
) {}
