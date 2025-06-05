package com.MediServe.apiMediServe.dto.patient;

import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.dto.patient.PatientDTO;
import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {

    private final AddressMapper addressMapper;
    private final AppointmentMapper appointmentMapper;


    public PatientDTO toDTO(Patient patient){
        if (patient == null){
            return null;
        }

        return new PatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getPhone(),
                addressMapper.toDTO(patient.getAddress()),
                patient.getAppointments().stream().map(appointment -> appointmentMapper.toDTO(appointment)).toList(),
                patient.getUser().getId()
        );
    }

    public Patient toEntity(PatientDTO patientDTO){
        if (patientDTO == null){
            return null;
        }

        Patient patient = new Patient();
        patient.setName(patientDTO.name());
        patient.setCpf(patientDTO.cpf());
        patient.setPhone(patientDTO.phone());
        patient.setAddress(addressMapper.toEntity(patientDTO.address()));

        return patient;
    }
}
