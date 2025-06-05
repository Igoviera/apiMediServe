package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.patient.PatientDTO;
import com.MediServe.apiMediServe.model.Patient;

import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    List<PatientDTO> findAllPatients();
    PatientDTO findByPatientId(Long id);
}
