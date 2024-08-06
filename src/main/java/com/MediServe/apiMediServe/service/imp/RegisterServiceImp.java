package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.*;
import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.service.DoctorService;
import com.MediServe.apiMediServe.service.PatientService;
import com.MediServe.apiMediServe.service.RegisterService;
import com.MediServe.apiMediServe.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImp implements RegisterService {

    private final UserService userService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Override
    @Transactional
    public DoctorDTO registerDoctor(DoctorRegistrationDTO doctorRegistrationDTO) {
        // Cria o usuário
        UserDTO userDTO = new UserDTO(
                null,
                doctorRegistrationDTO.username(),
                doctorRegistrationDTO.password(),
                UserRole.MEDICO,
                null,
                null
        );

        UserDTO createdUser = userService.createUser(userDTO);

        // Criar o médico
        DoctorDTO doctorDTO = new DoctorDTO(
                null,
                doctorRegistrationDTO.name(),
                doctorRegistrationDTO.imgUrl(),
                doctorRegistrationDTO.crm(),
                doctorRegistrationDTO.cpf(),
                doctorRegistrationDTO.phone(),
                doctorRegistrationDTO.queryValue(),
                doctorRegistrationDTO.address(),
                doctorRegistrationDTO.specialties(),
                doctorRegistrationDTO.openingHours(),
                doctorRegistrationDTO.clinicId(),
                createdUser.id(),
                true
        );

        return doctorService.createDoctor(doctorDTO);
    }

    @Override
    @Transactional
    public PatientDTO registerPatient(PatientRegistrationDTO patientRegistrationDTO) {
        UserDTO userDTO = new UserDTO(
                null,
                patientRegistrationDTO.username(),
                patientRegistrationDTO.password(),
                UserRole.PACIENTE,
                null,
                null
        );
        UserDTO createdUser = userService.createUser(userDTO);

        PatientDTO patientDTO = new PatientDTO(
                null,
                patientRegistrationDTO.name(),
                patientRegistrationDTO.cpf(),
                patientRegistrationDTO.sex(),
                patientRegistrationDTO.phone(),
                patientRegistrationDTO.birthDate(),
                patientRegistrationDTO.address(),
                createdUser.id()
        );

        return patientService.createPatient(patientDTO);
    }
}
