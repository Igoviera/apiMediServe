package com.MediServe.apiMediServe.dto.user;

import com.MediServe.apiMediServe.enums.UserRole;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        UserRole role
) {}
