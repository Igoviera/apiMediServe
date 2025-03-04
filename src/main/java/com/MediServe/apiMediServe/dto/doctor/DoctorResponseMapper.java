package com.MediServe.apiMediServe.dto.doctor;

import com.MediServe.apiMediServe.model.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DoctorResponseMapper{

    public DoctorResponseDTO toDTO(Doctor doctor){
        if(doctor == null){
            return null;
        }

        List<String> specialtyNames = doctor.getSpecialties().stream()
                .map(specialty -> specialty.getName())
                .collect(Collectors.toList());

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getImgUrl(),
                doctor.getCrm(),
                doctor.getQueryValue(),
                specialtyNames,
                doctor.getClinicId().getId(),
                doctor.getUser().getId(),
                doctor.isStatus()
        );
    }
}
