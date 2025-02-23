package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {
    private final AddressMapper addressMapper;
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
                patient.getBirthDate(),
                addressMapper.toDTO(patient.getAddress()),
                patient.getUser().getId()
        );
    }

    public Patient toEntity(PatientDTO patientDTO){
        if (patientDTO == null){
            return null;
        }

        Patient patient = new Patient();
        patient.setName(patientDTO.nome());
        patient.setCpf(patientDTO.cpf());
        patient.setSex(patientDTO.sex());
        patient.setPhone(patientDTO.phone());
        patient.setBirthDate(patientDTO.birthDate());
        patient.setAddress(addressMapper.toEntity(patientDTO.address()));

        return patient;
    }
}
