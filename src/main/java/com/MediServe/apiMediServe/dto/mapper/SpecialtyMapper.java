package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.SpecialtyDTO;
import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SpecialtyMapper {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyDTO toDTO(Specialty specialty){
        if (specialty == null){
            return null;
        }

        return new SpecialtyDTO(
                specialty.getId(),
                specialty.getName(),
                null
        );
    }

    public Specialty toEntity(Long specialtyId){
        if (specialtyId == null) {
            return null;
        }

        return specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new RuntimeException("Specialty not found with id: " + specialtyId));
    }
}
