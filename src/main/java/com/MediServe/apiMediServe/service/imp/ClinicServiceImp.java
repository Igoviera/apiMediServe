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
    public ClinicDTO createClinic(ClinicDTO clinicDTO) {
        return clinicMapper.toDTO(clinicRepository.save(clinicMapper.toEntity(clinicDTO)));
    }

    @Override
    public List<ClinicDTO> findAllClinic() {
        return clinicRepository.findAll().stream()
                .map(clinic -> clinicMapper.toDTO(clinic))
                .collect(Collectors.toList());
    }

    @Override
    public ClinicDTO findByIdClinic(Long id) {
        return clinicMapper.toDTO(clinicRepository.findById(id).
                orElseThrow(() -> new RecordNotFoundException(id)));

    }

    @Override
    public ClinicDTO updateClinic(Long clinicId, ClinicDTO clinicDTO) {
        return clinicRepository.findById(clinicId)
                .map(recordFound -> {
                    Clinic updateClinic = clinicMapper.toEntity(clinicDTO);
                    recordFound.setName(updateClinic.getName());
                    recordFound.setEmail(updateClinic.getEmail());
                    recordFound.setPhone(updateClinic.getPhone());
                    recordFound.setOpeningTime(updateClinic.getOpeningTime());
                    recordFound.setClosingTime(updateClinic.getClosingTime());
                    return clinicMapper.toDTO(clinicRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(clinicId));
    }
}
