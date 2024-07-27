package com.MediServe.apiMediServe.mapper.clinic;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.model.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ClinicMapper {

    public ClinicDTO toDTO(Clinic clinic){
        if (clinic == null){
            return null;
        }

        return new ClinicDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getEmail(),
                clinic.getPhones().toString(),
                clinic.getImgURL()
        );
    }

    public Clinic toEntity(ClinicDTO clinicDTO){
        if (clinicDTO == null){
            return null;
        }

        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.name());
        clinic.setEmail(clinicDTO.email());
        clinic.setPhones(Collections.singletonList(clinicDTO.phone()));
        clinic.setImgURL(clinicDTO.imgURL());

        return clinic;
    }
}
