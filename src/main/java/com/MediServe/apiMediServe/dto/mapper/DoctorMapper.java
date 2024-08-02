package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.DoctorDTO;
import com.MediServe.apiMediServe.model.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DoctorMapper {

    private final AddressMapper addressMapper;
    private final SpecialtyMapper specialtyMapper;
    private final OpeningHoursMapper openingHoursMapper;
    private final ClinicMapper clinicMapper;
    private final UserMapper userMapper;

    public DoctorDTO toDTO(Doctor doctor){
        if (doctor == null){
            return null;
        }

        return new DoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getImgUrl(),
                doctor.getCrm(),
                doctor.getCpf(),
                doctor.getPhone(),
                doctor.getQueryValue(),
                addressMapper.toDTO(doctor.getAddress()),
                doctor.getSpecialties().stream()
                        .map(specialty -> specialtyMapper.toDTO(specialty))
                        .collect(Collectors.toList()),
                doctor.getOpeningHours().stream()
                        .map(openingHours -> openingHoursMapper.toDTO(openingHours))
                        .collect(Collectors.toList()),
                clinicMapper.toDTO(doctor.getClinic()),
                userMapper.toDTO(doctor.getUser()),
                doctor.isStatus()
        );
    }

    public Doctor toEntity(DoctorDTO doctorDTO){
        if (doctorDTO == null){
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.name());
        doctor.setImgUrl(doctorDTO.imgUrl());
        doctor.setCrm(doctorDTO.crm());
        doctor.setCpf(doctorDTO.cpf());
        doctor.setPhone(doctorDTO.phone());
        doctor.setQueryValue(doctorDTO.queryValue());
        doctor.setAddress(addressMapper.toEntity(doctorDTO.addressDTO()));
        doctor.setSpecialties(doctorDTO.specialties().stream()
                .map(specialtyDTO -> specialtyMapper.toEntity(specialtyDTO))
                .collect(Collectors.toList()));
        doctor.setOpeningHours(doctorDTO.openingHours().stream()
                .map(openingHoursDTO -> openingHoursMapper.toEntity(openingHoursDTO))
                .collect(Collectors.toList()));
        doctor.setClinic(clinicMapper.toEntity(doctorDTO.clinic()));
        doctor.setUser(userMapper.toEntity(doctorDTO.user()));
        doctor.setStatus(doctorDTO.status());

        return doctor;
    }
}
