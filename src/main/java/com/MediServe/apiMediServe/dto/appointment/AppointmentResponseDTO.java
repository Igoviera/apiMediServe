package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        Long id,
        String clinicName,
        String doctorName,
        String patientName,
        DayOfWeek dayOfWeek,
        LocalTime time,
        StatusAppointment status
) {
}
