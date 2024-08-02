package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.SpecialtyDTO;
import com.MediServe.apiMediServe.model.Specialty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SpecialtyMapper {
    private final DoctorMapper doctorMapper;

    public SpecialtyDTO toDTO(Specialty specialty){
        if (specialty == null){
            return null;
        }

        return new SpecialtyDTO(
                specialty.getId(),
                specialty.getName(),
                specialty.getDoctors().stream()
                        .map(doctor -> doctorMapper.toDTO(doctor))
                        .collect(Collectors.toList())
        );
    }

    public Specialty toEntity(SpecialtyDTO specialtyDTO){
        if (specialtyDTO == null) {
            return null;
        }

        Specialty specialty = new Specialty();
        specialty.setName(specialtyDTO.name());
        specialty.setDoctors(specialtyDTO.doctorDTOS().stream()
                .map(doctorDTO -> doctorMapper.toEntity(doctorDTO))
                .collect(Collectors.toList()));

        return specialty;
    }
}
