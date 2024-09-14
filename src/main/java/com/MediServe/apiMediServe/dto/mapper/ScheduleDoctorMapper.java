package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.ScheduleDoctorDTO;
import com.MediServe.apiMediServe.model.ScheduleDoctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduleDoctorMapper {
    public ScheduleDoctorDTO toDTO(ScheduleDoctor scheduleDoctor){
        if (scheduleDoctor == null) {
            return null;
        }

        return new ScheduleDoctorDTO(
                scheduleDoctor.getId(),
                scheduleDoctor.getDayOfWeek(),
                scheduleDoctor.getStartTime(),
                scheduleDoctor.getEndTime(),
                null
        );
    }

    public ScheduleDoctor toEntity(ScheduleDoctorDTO scheduleDoctorDTO){
        if (scheduleDoctorDTO == null) {
            return null;
        }

        ScheduleDoctor scheduleDoctor = new ScheduleDoctor();
        scheduleDoctor.setDayOfWeek(scheduleDoctorDTO.dayOfWeek());
        scheduleDoctor.setStartTime(scheduleDoctorDTO.startTime());
        scheduleDoctor.setEndTime(scheduleDoctorDTO.endTime());

        return scheduleDoctor;
    }
}
