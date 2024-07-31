package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.model.Patient;

import java.util.List;

public interface PatientService {
    PatientDTO createPatient(PatientDTO patientDTO);
    Patient getByIdPatient(Long id);
    List<Patient> getAllPatient();
}
