package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.mapper.PatientMapper;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.User;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.UserRespository;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService {
    private final PatientRepository patientRepository;
    private final UserRespository userRespository;

    private final PatientMapper patientMapper;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        User user = new User();
        user.setUsername(patientDTO.username());
        user.setPassword(patientDTO.password());

        userRespository.save(user);

        return patientMapper.toDTO(patientRepository.save(patientMapper.toEntity(patientDTO, user)));
    }

    @Override
    public Patient getByIdPatient(Long id) {
        return null;
    }

    @Override
    public List<Patient> getAllPatient() {
        return null;
    }
}
