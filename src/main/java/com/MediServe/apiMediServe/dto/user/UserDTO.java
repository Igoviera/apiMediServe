package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(
        Long id,

        @NotBlank(message = "O nome de usuário é obrigatório")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @NotBlank(message = "O papel do usuário é obrigatório")
        UserRole roles
) {}
