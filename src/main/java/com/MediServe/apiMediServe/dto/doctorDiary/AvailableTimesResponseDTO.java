package com.MediServe.apiMediServe.dto.doctorDiary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record AvailableTimesResponseDTO(
        LocalDate date,
        List<LocalTime> schedules

) {
}
