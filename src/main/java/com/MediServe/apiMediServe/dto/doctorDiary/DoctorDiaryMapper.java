package com.MediServe.apiMediServe.dto.doctorDiary;

import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.DoctorDiary;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class DoctorDiaryMapper {

    public DoctorDiaryResponseDTO toDTO(DoctorDiary entity) {
        if (entity == null){
            return null;
        }
        return new DoctorDiaryResponseDTO(
                entity.getId(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getDurationMinutes(),
                entity.getDoctor().getId()
        );
    }

    public DoctorDiary toEntity(DoctorDiaryRequestDTO dto, Doctor doctor) {
        DoctorDiary diary = new DoctorDiary();
        diary.setDayOfWeek(fromPortuguese(dto.dayOfWeek()));
        diary.setStartTime(dto.startTime());
        diary.setEndTime(dto.endTime());
        diary.setDurationMinutes(dto.durationMinutes());
        diary.setDoctor(doctor);
        return diary;
    }


    public static DayOfWeek fromPortuguese(String dia) {
        switch (dia.toLowerCase()) {
            case "segunda":
            case "segunda-feira":
                return DayOfWeek.MONDAY;
            case "terça":
            case "terça-feira":
                return DayOfWeek.TUESDAY;
            case "quarta":
            case "quarta-feira":
                return DayOfWeek.WEDNESDAY;
            case "quinta":
            case "quinta-feira":
                return DayOfWeek.THURSDAY;
            case "sexta":
            case "sexta-feira":
                return DayOfWeek.FRIDAY;
            case "sábado":
            case "sabado":
                return DayOfWeek.SATURDAY;
            case "domingo":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Dia da semana inválido: " + dia);
        }
    }
}
