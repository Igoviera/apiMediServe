package com.MediServe.apiMediServe.dto.clinic;

import com.MediServe.apiMediServe.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalTime;


public record ClinicDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String name,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @NotBlank(message = "O horário de abertura é obrigatório")
        String openingTime,

        @NotBlank(message = "O horário de fechamento é obrigatório")
        String closingTime,

        String imgURL,

        @Valid
        AddressDTO address
){}

