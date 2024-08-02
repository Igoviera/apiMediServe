package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.UserDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@AllArgsConstructor
public class UserMapper {
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                patientMapper.toDTO(user.getPatient()),
                doctorMapper.toDTO(user.getDoctor())
        );
    }

    public User toEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }

        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRole(userDTO.role());
        user.setPatient(patientMapper.toEntity(userDTO.patientDTO(), user));
        user.setDoctor(doctorMapper.toEntity(userDTO.doctorDTO()));

        return user;
    }
}
