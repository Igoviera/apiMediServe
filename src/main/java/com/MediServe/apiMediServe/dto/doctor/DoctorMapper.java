package com.MediServe.apiMediServe.dto.doctor;

import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.dto.specialty.SpecialtyMapper;
import com.MediServe.apiMediServe.dto.user.UserMapper;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DoctorMapper {

    private final ClinicRepository clinicRepository;
    private final AddressMapper addressMapper;
    private final SpecialtyMapper specialtyMapper;
    private final UserMapper userMapper;

    public DoctorResponseDTO toDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getImgUrl(),
                doctor.getCrm(),
                doctor.getCpf(),
                doctor.getPhone(),
                doctor.getDescription(),
                doctor.getQueryValue(),
                addressMapper.toDTO(doctor.getAddress()),
                doctor.getSpecialties().stream()
                        .map(Specialty::getId)
                        .collect(Collectors.toList()),
                doctor.getStatus(),
                doctor.getClinicId().getId(),
                userMapper.toDTO(doctor.getUser())
        );
    }

    public Doctor toEntity(DoctorRequestDTO doctorRequestDTO) {
        if (doctorRequestDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDTO.name());
        doctor.setImgUrl(doctorRequestDTO.imgUrl());
        doctor.setCrm(doctorRequestDTO.crm());
        doctor.setCpf(doctorRequestDTO.cpf());
        doctor.setPhone(doctorRequestDTO.phone());
        doctor.setDescription(doctorRequestDTO.description());
        doctor.setQueryValue(doctorRequestDTO.queryValue());
        doctor.setAddress(addressMapper.toEntity(doctorRequestDTO.address()));

        // Clinic association
        doctor.setClinicId(clinicRepository.findById(doctorRequestDTO.clinicId())
                .orElseThrow(() -> new RuntimeException("Clinic not found with id: " + doctorRequestDTO.clinicId())));

        doctor.setSpecialties(doctor.getSpecialties());
        doctor.setStatus(doctorRequestDTO.status());
        doctor.setUser(userMapper.toEntity(doctorRequestDTO.user()));

        return doctor;
    }
}
