package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.DoctorDTO;
import com.MediServe.apiMediServe.dto.mapper.ClinicMapper;
import com.MediServe.apiMediServe.dto.mapper.DoctorMapper;
import com.MediServe.apiMediServe.dto.mapper.UserMapper;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.*;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import com.MediServe.apiMediServe.repository.UserRespository;
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
    private final UserRespository userRespository;

    private final ClinicMapper clinicMapper;
    private final DoctorMapper doctorMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);

        // Validar e associar a clínica
        Clinic clinic = clinicRepository.findById(doctorDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException(doctorDTO.clinicId()));
        doctor.setClinic(clinic);

        // Validar e associar as especialidades
        List<Specialty> specialties = doctorDTO.specialtyIds().stream()
                .map(specialtyId -> specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new RecordNotFoundException(specialtyId)))
                .collect(Collectors.toList());
        doctor.setSpecialties(specialties);

        // Associar as horas de funcionamento
        Doctor finalDoctor = doctor;
        doctor.setDoctorDiaries(doctorDTO.openingHours().stream()
                .map(openingHoursDTO -> new ScheduleDoctor(
                        openingHoursDTO.id(),
                        openingHoursDTO.dayOfWeek(),
                        openingHoursDTO.startTime(),
                        openingHoursDTO.endTime(),
                        finalDoctor))
                .collect(Collectors.toList()));

        // Buscar e associar o usuário
        User user = userRespository.findById(doctorDTO.userId())
                .orElseThrow(() -> new RecordNotFoundException(doctorDTO.userId()));
        doctor.setUser(user);

        // Salvar o médico
        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }
    @Override
    public List<DoctorDTO> getAllDoctor() {
        return doctorRepository.findAll().stream()
                .map(doctor -> doctorMapper.toDTO(doctor))
                .collect(Collectors.toList());
    }

    @Override
    public Doctor getByIdDoctor(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        return doctorRepository.findById(id)
                .map(existDoctor -> {
                    existDoctor.setName(doctor.getName());
                    existDoctor.setImgUrl(doctor.getImgUrl());
                    existDoctor.setCrm(doctor.getCrm());
                    existDoctor.setCpf(doctor.getCpf());
                    existDoctor.setPhone(doctor.getPhone());
                    existDoctor.setQueryValue(doctor.getQueryValue());
                    existDoctor.setAddress(doctor.getAddress());

                    return doctorRepository.save(existDoctor);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
