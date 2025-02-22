package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.model.Doctor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SpecialtyDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        String name,
        List<DoctorDTO> doctorDTOS
) {}
