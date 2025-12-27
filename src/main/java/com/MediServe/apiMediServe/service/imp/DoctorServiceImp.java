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
import com.MediServe.apiMediServe.repository.UserRepository;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        if (userRepository.existsByEmail(doctorRequestDTO.user().email())){
            throw new EmailAlreadyExists();
        }
        Doctor doctor = doctorMapper.toEntity(doctorRequestDTO);

//        String encodedPassword = passwordEncoder.encode(doctor.getUser().getPassword());
//        doctor.getUser().setPassword(encodedPassword);

        User user = userMapper.toEntity(doctorRequestDTO.user());
        user.setStatus(UserStatus.ATIVO);
        user.setRole(UserRole.MEDICO);
        userRepository.save(user);

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
        return doctorRepository.findAllByStatus(DoctorStatus.ATIVO,pageable)
                .map(doctor -> doctorMapper.toDTO(doctor));
    }

    @Override
    public DoctorResponseDTO getByIdDoctor(Long id) {
        return doctorMapper.toDTO(doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    @Override
    public void remove(Long id) {
      Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
      doctor.setStatus(DoctorStatus.INATIVO);
      doctorRepository.save(doctor);
    }

    @Override
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO doctorRequestDTO) {
        Doctor doctorFound = doctorRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));

        doctorFound.setName(doctorRequestDTO.name());
        doctorFound.setImgUrl(doctorRequestDTO.imgUrl());
        doctorFound.setCrm(doctorRequestDTO.crm());
        doctorFound.setCrm(doctorRequestDTO.crm());
        doctorFound.setCpf(doctorRequestDTO.cpf());
        doctorFound.setPhone(doctorRequestDTO.phone());
        doctorFound.setDescription(doctorRequestDTO.description());
        doctorFound.setQueryValue(doctorRequestDTO.queryValue());
        doctorFound.setAddress(addressMapper.toEntity(doctorRequestDTO.address()));

        if (doctorRequestDTO.specialtyIds() != null) {
            List<Specialty> specialties = specialtyRepository.findAllById(doctorRequestDTO.specialtyIds());

            doctorFound.getSpecialties().clear();
            doctorFound.getSpecialties().addAll(specialties);
        }

        User user = doctorFound.getUser();
        user.setUsername(doctorRequestDTO.user().username());
        user.setEmail(doctorRequestDTO.user().email());
        user.setRole(UserRole.MEDICO);
        user.setStatus(UserStatus.ATIVO);
        user.setPassword(doctorRequestDTO.user().password());

        if (doctorRequestDTO.user().password() != null){
            user.setPassword(passwordEncoder.encode(doctorRequestDTO.user().password()));
        }

        return doctorMapper.toDTO(doctorRepository.save(doctorFound));
    }
}
