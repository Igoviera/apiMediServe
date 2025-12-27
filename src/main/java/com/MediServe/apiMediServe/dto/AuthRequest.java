package com.MediServe.apiMediServe.dto;

public record AuthRequest(
    String email,
    String password
) {
}
