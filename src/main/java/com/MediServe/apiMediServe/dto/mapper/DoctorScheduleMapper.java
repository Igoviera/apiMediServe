package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.DoctorScheduleDTO;
import com.MediServe.apiMediServe.model.DoctorSchedule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DoctorScheduleMapper {
    public DoctorScheduleDTO toDTO(DoctorSchedule doctorSchedule){
        if (doctorSchedule == null) {
            return null;
        }

        return new DoctorScheduleDTO(
                doctorSchedule.getId(),
                doctorSchedule.getDayOfWeek(),
                doctorSchedule.getStartTime(),
                doctorSchedule.getEndTime(),
                null
        );
    }

    public DoctorSchedule toEntity(DoctorScheduleDTO doctorScheduleDTO){
        if (doctorScheduleDTO == null) {
            return null;
        }

        DoctorSchedule doctorSchedule = new DoctorSchedule();
        doctorSchedule.setDayOfWeek(doctorScheduleDTO.dayOfWeek());
        doctorSchedule.setStartTime(doctorScheduleDTO.startTime());
        doctorSchedule.setEndTime(doctorScheduleDTO.endTime());

        return doctorSchedule;
    }
}
