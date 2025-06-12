package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.patient.PatientRequestDTO;
import com.MediServe.apiMediServe.dto.patient.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    List<PatientResponseDTO> findAllPatients();
    PatientResponseDTO findByPatientId(Long id);
}
