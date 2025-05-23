package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.enums.StatusAppointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(
       Long id,
       String patientName,
       String doctorName,
       LocalDateTime data,
       StatusAppointment status
) {}
