package com.MediServe.apiMediServe.dto.doctorDiary;
import java.util.List;

public record AvailableTimesDTO(
        String day,
        List<String> timeSlosts
) {
}
