package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.specialty.SpecialtyDTO;
import com.MediServe.apiMediServe.dto.specialty.SpecialtyMapper;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import com.MediServe.apiMediServe.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImp implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    @Override
    public SpecialtyDTO createSpecialty(SpecialtyDTO specialtyDTO) {
        return specialtyMapper.toDTO(specialtyRepository.save(specialtyMapper.toEntity(specialtyDTO)));
    }

    @Override
    public List<SpecialtyDTO> getAllSpecialty() {
        List<Specialty> specialties = specialtyRepository.findAll();

        return specialties.stream()
                .map(specialty -> specialtyMapper.toDTO(specialty))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialtyDTO getByIdSpecialty(Long id) {
        return specialtyMapper.toDTO(specialtyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public SpecialtyDTO updateSpecialty(Long id, SpecialtyDTO specialtyDTO) {
        Specialty existengSpecialty = specialtyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));


        existengSpecialty.setName(specialtyDTO.name());

       return specialtyMapper.toDTO(specialtyRepository.save(existengSpecialty));
    }
}
