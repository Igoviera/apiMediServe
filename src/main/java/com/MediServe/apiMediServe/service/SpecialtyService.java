package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.specialty.SpecialtyDTO;

import java.util.List;

public interface SpecialtyService {
    SpecialtyDTO createSpecialty(SpecialtyDTO specialtyDTO);
    List<SpecialtyDTO> getAllSpecialty();
    SpecialtyDTO getByIdSpecialty(Long id);
    SpecialtyDTO updateSpecialty(Long id, SpecialtyDTO specialtyDTO);
}
