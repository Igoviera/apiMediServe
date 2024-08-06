package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.UserDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@AllArgsConstructor
public class UserMapper {
    //private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getPatient() != null? user.getPatient().getId() : null,
                user.getDoctor() != null? user.getDoctor().getId() : null
        );
    }

    public User toEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.roles());
        // Associar Patient se patientId não for nulo
        if (userDTO.patientId() != null) {
            Patient patient = patientRepository.findById(userDTO.patientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found with id " + userDTO.patientId()));
            user.setPatient(patient);
        }

        // Associar Doctor se doctorId não for nulo
        if (userDTO.doctorId() != null) {
            Doctor doctor = doctorRepository.findById(userDTO.doctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found with id " + userDTO.doctorId()));
            user.setDoctor(doctor);
        }
        return user;
    }
}
