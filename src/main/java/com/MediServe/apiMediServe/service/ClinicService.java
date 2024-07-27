package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.model.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(Clinic clinic);
    List<ClinicDTO> findAllClinic();
    Clinic findByIdClinic(Long id);
    Clinic updateClinic(Long clinicId, Clinic clinic);
}
