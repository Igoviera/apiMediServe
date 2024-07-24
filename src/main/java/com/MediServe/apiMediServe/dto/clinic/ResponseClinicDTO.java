package com.MediServe.apiMediServe.dto.clinic;

public record ResponseClinicDTO(
        Long id,
        String name,
        String email,
        String phone,
        String imgURL
){}
