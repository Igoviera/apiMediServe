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

        @NotEmpty(message = "O horário de abertura é obrigatório")
        @Pattern(
                regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$",
                message = "O telefone deve estar no formato (XX) XXXXX-XXXX"
        )

        @NotNull(message = "O horário de abertura é obrigatório")
        LocalTime openingTime,

        @NotNull(message = "O horário de fechamento é obrigatório")
        LocalTime closingTime,

        String imgURL,

        @Valid
        AddressDTO address
){}

