package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDTO toDTO(Patient patient){
        if (patient == null){
            return null;
        }

        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getSex(),
                patient.getPhone(),
                patient.getBirthDate()
        );
    }

    public Patient toEntity(PatientDTO patientDTO, User user){
        if (patientDTO == null){
            return null;
        }

        Patient patient = new Patient();
        patient.setUser(user);
        patient.setName(patientDTO.nome());
        patient.setCpf(patientDTO.cpf());
        patient.setSex(patientDTO.sex());
        patient.setPhone(patientDTO.phone());
        patient.setBirthDate(patientDTO.birthDate());
        return patient;
    }
}
