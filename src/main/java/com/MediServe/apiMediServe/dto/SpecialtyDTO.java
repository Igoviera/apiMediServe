package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.model.Doctor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;

import java.util.List;

public record SpecialtyDTO(
        Long id,
        String name,
        List<DoctorDTO> doctorDTOS
) {}
