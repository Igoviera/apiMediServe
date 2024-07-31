package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import jakarta.persistence.*;

public record UserDTO(
        Long id,
        String username,
        String password,
        UserRole role,
        PatientDTO patientDTO,
        DoctorDTO doctorDTO
) {}
