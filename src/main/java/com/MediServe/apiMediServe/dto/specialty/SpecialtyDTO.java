package com.MediServe.apiMediServe.dto.specialty;

import com.MediServe.apiMediServe.model.Doctor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SpecialtyDTO(
        Long id,
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 50, message = "A especialidade deve ter entre 2 é 50 caracteres")
        String name,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<Long> doctorIds
) {}
