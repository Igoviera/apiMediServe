package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.model.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> getAllDoctor();
    Doctor getByIdDoctor(Long id);
    Doctor updateDoctor(Long id, Doctor doctor);
}
