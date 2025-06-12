package com.MediServe.apiMediServe.dto.patient;

import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.dto.user.UserMapper;
import com.MediServe.apiMediServe.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PatientMapper {

    private final AddressMapper addressMapper;
    private final AppointmentMapper appointmentMapper;
    private final UserMapper userMapper;

    public PatientResponseDTO toDTO(Patient patient){
        if (patient == null){
            return null;
        }

        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getCpf(),
                patient.getPhone(),
                addressMapper.toDTO(patient.getAddress()),
                //patient.getAppointments().stream().map(appointment -> appointmentMapper.toDTO(appointment)).toList(),
                userMapper.toDTO(patient.getUser())
        );
    }

    public Patient toEntity(PatientRequestDTO patientRequestDTO){
        if (patientRequestDTO == null){
            return null;
        }

        Patient patient = new Patient();
        patient.setName(patientRequestDTO.name());
        patient.setCpf(patientRequestDTO.cpf());
        patient.setPhone(patientRequestDTO.phone());
        patient.setAddress(addressMapper.toEntity(patientRequestDTO.address()));
        patient.setUser(userMapper.toEntity(patientRequestDTO.user()));

        return patient;
    }
}
