package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorMapper;
import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseMapper;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.*;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.SpecialtyRepository;
import com.MediServe.apiMediServe.repository.UserRespository;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final DoctorMapper doctorMapper;
    private final DoctorResponseMapper doctorResponseMapper;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);

        // Validar e associar a clínica
        Clinic clinic = clinicRepository.findById(doctorDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException(doctorDTO.clinicId()));
        doctor.setClinicId(clinic);

        // Validar e associar as especialidades
        List<Specialty> specialties = doctorDTO.specialtyIds().stream()
                .map(specialtyId -> specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new RecordNotFoundException(specialtyId)))
                .collect(Collectors.toList());
        doctor.setSpecialties(specialties);
<<<<<<< HEAD

=======
>>>>>>> develop
        // Buscar e associar o usuário
        User user = userRespository.findById(doctorDTO.userId())
                .orElseThrow(() -> new RecordNotFoundException(doctorDTO.userId()));

        doctor.setUser(user);

        // Salvar o médico
        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Override
    public Page<DoctorResponseDTO> getAllDoctor(Pageable pageable) {
        return doctorRepository.findAll(pageable)
                .map(doctor -> doctorResponseMapper.toDTO(doctor));
    }

    @Override
    public DoctorResponseDTO getByIdDoctor(Long id) {
        return doctorResponseMapper.toDTO(doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO) {
        return doctorRepository.findById(id)
                .map(existDoctor -> {
                    existDoctor.setName(doctorDTO.name());
                    existDoctor.setImgUrl(doctorDTO.imgUrl());
                    existDoctor.setCrm(doctorDTO.crm());
                    existDoctor.setCpf(doctorDTO.cpf());
                    existDoctor.setPhone(doctorDTO.phone());
                    existDoctor.setQueryValue(doctorDTO.queryValue());
                    existDoctor.setDescription(doctorDTO.description());
                    existDoctor.setAddress(addressMapper.toEntity(doctorDTO.address()));

                    return doctorMapper.toDTO(doctorRepository.save(existDoctor));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
