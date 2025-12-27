package com.MediServe.apiMediServe.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    PACIENTE,
    MEDICO,
    ADMIN,
    RECEPCIONISTA;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
