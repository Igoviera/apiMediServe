package com.MediServe.apiMediServe.dto.patient;

import com.MediServe.apiMediServe.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientDTO(

        Long id,

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 é 100 caracteres")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @Valid
        AddressDTO address,

        Long userId
) {}
