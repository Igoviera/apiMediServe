package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import com.MediServe.apiMediServe.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    Page<DoctorResponseDTO> getAllDoctor(Pageable pageable);
    DoctorResponseDTO getByIdDoctor(Long id);
    DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);
}
