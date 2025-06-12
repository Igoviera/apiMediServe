package com.MediServe.apiMediServe.dto.patient;

import com.MediServe.apiMediServe.dto.address.AddressDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.dto.user.UserRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PatientRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 é 100 caracteres")
        String name,

        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$", message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O telefone é obrigatório")
        String phone,

        @Valid
        AddressDTO address,

        List<AppointmentDTO> appointments,

        @Valid
        UserRequestDTO user
) {}
