package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.ClinicDTO;
import com.MediServe.apiMediServe.exception.InvalidTimeFormatException;
import com.MediServe.apiMediServe.model.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@AllArgsConstructor
public class ClinicMapper {
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    public ClinicDTO toDTO(Clinic clinic){

        if (clinic == null){
            return null;
        }

        return new ClinicDTO(
                clinic.getId(),
                clinic.getName(),
                clinic.getEmail(),
                clinic.getPhone(),
                clinic.getOpeningTime() != null ? clinic.getOpeningTime().format(timeFormatter) : null,
                clinic.getClosingTime() != null ? clinic.getClosingTime().format(timeFormatter) : null,
                clinic.getImgURL()
        );
    }

    public Clinic toEntity(ClinicDTO clinicDTO){
        if (clinicDTO == null){
            return null;
        }

        Clinic clinic = new Clinic();
        clinic.setName(clinicDTO.name());
        clinic.setEmail(clinicDTO.email());
        clinic.setPhone(clinicDTO.phone());
        clinic.setImgURL(clinicDTO.imgURL());

        if (clinicDTO.openingTime() != null && !clinicDTO.openingTime().isEmpty()) {
            try {
                clinic.setOpeningTime(LocalTime.parse(clinicDTO.openingTime(), timeFormatter));
            } catch (DateTimeParseException e) {
                throw new InvalidTimeFormatException();
            }
        }

        if (clinicDTO.closingTime() != null){
            try{
                clinic.setClosingTime(LocalTime.parse(clinicDTO.closingTime(), timeFormatter));
            } catch (DateTimeParseException e) {
                throw new InvalidTimeFormatException();
            }
        }

        return clinic;
    }
}
