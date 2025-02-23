package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.model.Doctor;

import java.util.List;

public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    List<DoctorDTO> getAllDoctor();
    DoctorDTO getByIdDoctor(Long id);
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
}
