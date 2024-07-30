package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.OpeningHours;
import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ClinicRepository clinicRepository;

    @Override
    @Transactional
    public Doctor createDoctor(Doctor doctor) {
        Clinic clinic = clinicRepository.findById(doctor.getClinic().getId())
                .orElseThrow(() -> new RuntimeException("Clinica não encontrada com o id " + doctor.getClinic().getId()));
        doctor.setClinic(clinic);

        List<Specialty> specialties = doctor.getSpecialties().stream()
                .map(specialty -> specialtyRepository.findById(specialty.getId())
                        .orElseThrow(() -> new RuntimeException("Especialidade não encontrada com id " + specialty.getId())))
                .collect(Collectors.toList());

        doctor.setSpecialties(specialties);

        List<OpeningHours> openingHoursList = doctor.getOpeningHours();
        for (OpeningHours openingHours : openingHoursList){
            openingHours.setDoctor(doctor);
        }

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getByIdDoctor(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
