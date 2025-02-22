package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.DoctorDTO;
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
    private final ClinicMapper clinicMapper;

    public DoctorDTO toDTO(Doctor doctor) {
        if (doctor == null) {
            return null;
        }

        return new DoctorDTO(
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
                                openingHours.getId(),
                                openingHours.getDayOfWeek(),
                                openingHours.getStartTime(),
                                openingHours.getEndTime(),
                                doctor.getId()))
                        .collect(Collectors.toList()),
                doctor.getClinic().getId(),
                doctor.getUser() != null ? doctor.getUser().getId() : null,
                doctor.isStatus()
        );
    }

    public Doctor toEntity(DoctorDTO doctorDTO) {
        if (doctorDTO == null) {
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.name());
        doctor.setImgUrl(doctorDTO.imgUrl());
        doctor.setCrm(doctorDTO.crm());
        doctor.setCpf(doctorDTO.cpf());
        doctor.setPhone(doctorDTO.phone());
        doctor.setDescription(doctorDTO.description());
        doctor.setQueryValue(doctorDTO.queryValue());
        doctor.setAddress(addressMapper.toEntity(doctorDTO.address()));
        doctor.setClinic(clinicRepository.findById(doctorDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException(doctorDTO.clinicId())));
                        doctor))
                .collect(Collectors.toList()));
        doctor.setStatus(doctorDTO.status());

        return doctor;
    }
}
