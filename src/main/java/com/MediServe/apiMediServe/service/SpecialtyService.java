package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.model.Specialty;

import java.util.List;

public interface SpecialtyService {
    Specialty createSpecialty(Specialty specialty);
    List<Specialty> getAllSpecialty();
    Specialty getByIdSpecialty(Long id);
}
