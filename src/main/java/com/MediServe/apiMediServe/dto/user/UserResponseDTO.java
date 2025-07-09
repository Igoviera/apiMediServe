package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.enums.UserStatus;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        UserStatus status,
        UserRole role
) {}
