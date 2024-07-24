package com.MediServe.apiMediServe.mapper.clinic;

import com.MediServe.apiMediServe.dto.clinic.RequestClinicDTO;
import com.MediServe.apiMediServe.dto.clinic.ResponseClinicDTO;
import com.MediServe.apiMediServe.model.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class ClinicMapper {

    public ResponseClinicDTO toDTO(Clinic clinic){
        if (clinic == null){
            return null;
        }

        return new ResponseClinicDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getEmail(),
                clinic.getPhones().toString(),
                clinic.getImgURL()
        );
    }

    public Clinic toEntity(RequestClinicDTO requestClinicDTO){
        if (requestClinicDTO == null){
            return null;
        }

        Clinic clinic = new Clinic();
        clinic.setName(requestClinicDTO.name());
        clinic.setEmail(requestClinicDTO.email());
        clinic.setPhones(Collections.singletonList(requestClinicDTO.phone()));
        clinic.setImgURL(requestClinicDTO.imgURL());

        return clinic;
    }
}
