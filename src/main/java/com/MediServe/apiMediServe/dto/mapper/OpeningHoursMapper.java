package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.OpeningHoursDTO;
import com.MediServe.apiMediServe.model.DoctorDiary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OpeningHoursMapper {
    public OpeningHoursDTO toDTO(DoctorDiary doctorDiary){
        if (doctorDiary == null) {
            return null;
        }

        return new OpeningHoursDTO(
                doctorDiary.getId(),
                doctorDiary.getDayOfWeek(),
                doctorDiary.getStartTime(),
                doctorDiary.getEndTime(),
                null
        );
    }

    public DoctorDiary toEntity(OpeningHoursDTO openingHoursDTO){
        if (openingHoursDTO == null) {
            return null;
        }

        DoctorDiary doctorDiary = new DoctorDiary();
        doctorDiary.setDayOfWeek(openingHoursDTO.dayOfWeek());
        doctorDiary.setStartTime(openingHoursDTO.startTime());
        doctorDiary.setEndTime(openingHoursDTO.endTime());

        return doctorDiary;
    }
}
