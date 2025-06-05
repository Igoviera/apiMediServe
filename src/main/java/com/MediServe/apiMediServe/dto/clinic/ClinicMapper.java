package com.MediServe.apiMediServe.dto.clinic;

import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.model.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@AllArgsConstructor
public class ClinicMapper {

    private final AddressMapper addressMapper;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public ClinicDTO toDTO(Clinic clinic) {
        if (clinic == null) {
            return null;
        }

        return new ClinicDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getEmail(),
                clinic.getPhone(),
                clinic.getOpeningTime() != null ? clinic.getOpeningTime().format(TIME_FORMATTER) : null,
                clinic.getClosingTime() != null ? clinic.getClosingTime().format(TIME_FORMATTER) : null,
                clinic.getImgURL(),
                addressMapper.toDTO(clinic.getAddress())
        );
    }

    public Clinic toEntity(ClinicDTO clinicDTO) {
        if (clinicDTO == null) {
            return null;
        }

        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.name());
        clinic.setEmail(clinicDTO.email());
        clinic.setPhone(clinicDTO.phone());
        clinic.setImgURL(clinicDTO.imgURL());
        clinic.setAddress(addressMapper.toEntity(clinicDTO.address()));

        // Conversão segura de String para LocalTime
        try {
            clinic.setOpeningTime(clinicDTO.openingTime() != null ? LocalTime.parse(clinicDTO.openingTime(), TIME_FORMATTER) : null);
            clinic.setClosingTime(clinicDTO.closingTime() != null ? LocalTime.parse(clinicDTO.closingTime(), TIME_FORMATTER) : null);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de horário inválido. Use o formato HH:mm.");
        }

        return clinic;
    }
}
