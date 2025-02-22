package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;

import java.util.List;

public interface ClinicService {
    ClinicDTO createClinic(ClinicDTO clinicDTO);
    List<ClinicDTO> findAllClinic();
    ClinicDTO findByIdClinic(Long id);
    ClinicDTO updateClinic(Long clinicId, ClinicDTO clinicDTO);
}
