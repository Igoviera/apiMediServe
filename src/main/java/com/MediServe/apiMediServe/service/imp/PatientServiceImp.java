package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.patient.PatientRequestDTO;
import com.MediServe.apiMediServe.dto.patient.PatientMapper;
import com.MediServe.apiMediServe.dto.patient.PatientResponseDTO;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.UserRespository;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRespository userRespository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientMapper.toEntity(patientRequestDTO);
        userRespository.save(patient.getUser());
        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDTO findByPatientId(Long id) {
        return patientMapper.toDTO(patientRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public List<PatientResponseDTO> findAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> patientMapper.toDTO(patient))
                .collect(Collectors.toList());
    }
}
