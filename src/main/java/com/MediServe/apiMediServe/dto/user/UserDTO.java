package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        Long id,

        @NotBlank(message = "O nome de usuário é obrigatório")
        String username,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String password,

        @NotNull(message = "O papel do usuário é obrigatório")
        UserRole roles
) {}
