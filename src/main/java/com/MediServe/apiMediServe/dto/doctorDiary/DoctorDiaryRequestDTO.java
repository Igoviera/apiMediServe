package com.MediServe.apiMediServe.dto.doctorDiary;

import java.time.LocalTime;

public record DoctorDiaryRequestDTO(
        String dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        int durationMinutes,
        Long doctorId
) {
}
