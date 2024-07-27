package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.mapper.clinic.ClinicMapper;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicServiceImp implements ClinicService {

    private final ClinicRepository clinicRepository;

    private final ClinicMapper clinicMapper;

    @Override
    public Clinic createClinic(Clinic clinic) {
       return clinicRepository.save(clinic);
    }

    @Override
    public List<ClinicDTO> findAllClinic() {
        return clinicRepository.findAll().stream()
                .map(clinic -> clinicMapper.toDTO(clinic))
                .collect(Collectors.toList());
    }

    @Override
    public Clinic findByIdClinic(Long id) {
        return clinicRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Clinic updateClinic(Long clinicId, Clinic clinic) {
        Clinic clinic1 = findByIdClinic(clinicId);
        clinic1.setName(clinic.getName());
        clinic1.setEmail(clinic.getEmail());
        clinic1.setPhones(clinic.getPhones());

        return clinicRepository.save(clinic1);

    }
}
