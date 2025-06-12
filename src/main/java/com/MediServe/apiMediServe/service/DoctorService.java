package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.doctor.DoctorRequestDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {
    DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO);
    Page<DoctorResponseDTO> getAllDoctor(Pageable pageable);
    DoctorResponseDTO getByIdDoctor(Long id);
//    DoctorRequestDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO);
}
