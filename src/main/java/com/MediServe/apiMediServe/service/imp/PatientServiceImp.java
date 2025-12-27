package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.patient.PatientRequestDTO;
import com.MediServe.apiMediServe.dto.patient.PatientMapper;
import com.MediServe.apiMediServe.dto.patient.PatientResponseDTO;
import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.exception.EmailAlreadyExists;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.UserRepository;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (userRepository.existsByEmail(patientRequestDTO.user().email())) {
            throw new EmailAlreadyExists();
        }
        Patient patient = patientMapper.toEntity(patientRequestDTO);

        User user = patient.getUser();
        user.setRole(UserRole.PACIENTE);

        userRepository.save(user);
        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDTO findByPatientId(Long id) {
        return patientMapper.toDTO(patientRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado com o id: " + id));

        Patient patientEntity =  patientMapper.toEntity(patientRequestDTO);

        patient.setName(patientEntity.getName());
        patient.setCpf(patientEntity.getCpf());
        patient.setPhone(patientEntity.getPhone());
        patient.setAddress(patientEntity.getAddress());

        User user = patient.getUser();
        user.setUsername(patientEntity.getUser().getUsername());
        user.setEmail(patientEntity.getUser().getEmail());
        user.setPassword(patientEntity.getUser().getPassword());
        user.setRole(UserRole.PACIENTE);

        userRepository.save(user);

        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public List<PatientResponseDTO> findAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> patientMapper.toDTO(patient))
                .collect(Collectors.toList());
    }
}
