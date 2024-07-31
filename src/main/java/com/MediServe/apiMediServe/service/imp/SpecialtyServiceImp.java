package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import com.MediServe.apiMediServe.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImp implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    @Override
    public Specialty createSpecialty(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public List<Specialty> getAllSpecialty() {
        return specialtyRepository.findAll();
    }

    @Override
    public Specialty getByIdSpecialty(Long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Specialty updateSpecialty(Long id, Specialty specialty) {
        Specialty specialty1 = getByIdSpecialty(id);
        specialty1.setName(specialty.getName());

        return specialtyRepository.save(specialty1);
    }
}
