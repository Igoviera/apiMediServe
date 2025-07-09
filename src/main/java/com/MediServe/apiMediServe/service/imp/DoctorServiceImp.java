package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.doctor.DoctorRequestDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorMapper;
import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import com.MediServe.apiMediServe.dto.user.UserMapper;
import com.MediServe.apiMediServe.enums.DoctorStatus;
import com.MediServe.apiMediServe.enums.UserRole;
import com.MediServe.apiMediServe.enums.UserStatus;
import com.MediServe.apiMediServe.exception.EmailAlreadyExists;
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
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        if (userRespository.existsByEmail(doctorRequestDTO.user().email())){
            throw new EmailAlreadyExists();
        }
        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);

//        String encodedPassword = passwordEncoder.encode(doctor.getUser().getPassword());
//        doctor.getUser().setPassword(encodedPassword);

        User user = userMapper.toEntity(doctorRequestDTO.user());
        user.setStatus(UserStatus.ATIVO);
        user.setRole(UserRole.MEDICO);
        userRespository.save(user);

        doctor.setUser(user);

        // Validar e associar a clínica
        Clinic clinic = clinicRepository.findById(1L)
                .orElseThrow(() -> new RecordNotFoundException(1L));
        doctor.setClinic(clinic);

        // Validar e associar as especialidades
        List<Specialty> specialties = doctorRequestDTO.specialtyIds().stream()
                .map(specialtyId -> specialtyRepository.findById(specialtyId)
                        .orElseThrow(() -> new RecordNotFoundException(specialtyId)))
                .collect(Collectors.toList());
        doctor.setSpecialties(specialties);
        doctor.setStatus(DoctorStatus.ATIVO);

        // Salvar o médico
        return doctorMapper.toDTO(doctorRepository.save(doctor));
    }

    @Override
    public Page<DoctorResponseDTO> getAllDoctor(Pageable pageable) {
        return doctorRepository.findAll(pageable)
                .map(doctor -> doctorMapper.toDTO(doctor));
    }

    @Override
    public DoctorResponseDTO getByIdDoctor(Long id) {
        return doctorMapper.toDTO(doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

//    @Override
////    public DoctorRequestDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO) {
////        return doctorRepository.findById(id)
////                .map(existDoctor -> {
////                    existDoctor.setName(doctorRequestDTO.name());
////                    existDoctor.setImgUrl(doctorRequestDTO.imgUrl());
////                    existDoctor.setCrm(doctorRequestDTO.crm());
////                    existDoctor.setCpf(doctorRequestDTO.cpf());
////                    existDoctor.setPhone(doctorRequestDTO.phone());
////                    existDoctor.setQueryValue(doctorRequestDTO.queryValue());
////                    existDoctor.setDescription(doctorRequestDTO.description());
////                    existDoctor.setAddress(addressMapper.toEntity(doctorRequestDTO.address()));
////
////                    return doctorMapper.toDTO(doctorRepository.save(existDoctor));
////                }).orElseThrow(() -> new RecordNotFoundException(id));
////    }
}
