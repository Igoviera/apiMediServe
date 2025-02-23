package com.MediServe.apiMediServe.dto.specialty;

import com.MediServe.apiMediServe.model.Specialty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SpecialtyMapper {

    public SpecialtyDTO toDTO(Specialty specialty){
        if (specialty == null){
            return null;
        }

        // Verifica se a lista de médicos é null ou vazia e inicializa com uma lista vazia se for o caso
        List<Long> doctorsIds = (specialty.getDoctors() != null && !specialty.getDoctors().isEmpty()) ?
                specialty.getDoctors().stream()
                        .map(doctor -> doctor.getId())
                        .collect(Collectors.toList()) : new ArrayList<>();

        return new SpecialtyDTO(
                specialty.getId(),
                specialty.getName(),
                doctorsIds
        );
    }

    public Specialty toEntity(SpecialtyDTO specialtyDTO){
        if (specialtyDTO == null) {
            return null;
        }

        return new Specialty(
                specialtyDTO.id(),
                specialtyDTO.name(),
                null // Médicos ainda não associados aqui. Você pode implementar posteriormente se necessário.
        );
    }
}
