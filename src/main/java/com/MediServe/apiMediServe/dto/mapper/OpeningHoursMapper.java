package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.OpeningHoursDTO;
import com.MediServe.apiMediServe.model.OpeningHours;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OpeningHoursMapper {
    public OpeningHoursDTO toDTO(OpeningHours openingHours){
        if (openingHours == null) {
            return null;
        }

        return new OpeningHoursDTO(
                openingHours.getId(),
                openingHours.getDayOfWeek(),
                openingHours.getStartTime(),
                openingHours.getEndTime(),
                null
        );
    }

    public OpeningHours toEntity(OpeningHoursDTO openingHoursDTO){
        if (openingHoursDTO == null) {
            return null;
        }

        OpeningHours openingHours = new OpeningHours();
        openingHours.setDayOfWeek(openingHoursDTO.dayOfWeek());
        openingHours.setStartTime(openingHoursDTO.startTime());
        openingHours.setEndTime(openingHoursDTO.endTime());

        return openingHours;
    }
}
