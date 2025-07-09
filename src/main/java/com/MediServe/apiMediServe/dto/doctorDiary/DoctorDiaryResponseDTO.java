package com.MediServe.apiMediServe.dto.doctorDiary;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record DoctorDiaryResponseDTO(
        Long id,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        int durationMinutes,
        Long doctorId
) {
}
