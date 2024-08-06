package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.DoctorDTO;
import com.MediServe.apiMediServe.dto.SpecialtyDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Specialty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SpecialtyMapper {
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

    public Specialty toEntity(SpecialtyDTO specialtyDTO){
        if (specialtyDTO == null) {
            return null;
        }

        Specialty specialty = new Specialty();
        specialty.setName(specialtyDTO.name());

        return specialty;
    }
}
