package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.dto.mapper.PatientMapper;
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
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);

        User user = userRespository.findById(patientDTO.userId())
                .orElseThrow(() -> new RecordNotFoundException(patientDTO.userId()));
        patient.setUser(user);

        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public Patient getByIdPatient(Long id) {
        return null;
    }

    @Override
    public List<PatientDTO> findAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> patientMapper.toDTO(patient))
                .collect(Collectors.toList());
    }
}
