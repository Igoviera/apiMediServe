package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.model.Doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalTime;

public record ClinicDTO(
        Long id,
        @NotEmpty(message = "O nome é obrigatório")
        String name,
        @NotEmpty(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,
        @NotEmpty(message = "O telefone é obrigatório")
        String phone,
        @NotEmpty(message = "O horário de abertura é obrigatório")
        String openingTime,
        @NotEmpty(message = "O horário de fechamento é obrigatório")
        String closingTime,
        String imgURL,
        @Valid
        AddressDTO address
){}

