package com.MediServe.apiMediServe.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record DoctorAvailabilityResponseDTO (
        Long doctorId,
        LocalDate date,
        Integer durationMinutes,
        List<LocalTime> availableSlots
) {
}
