package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
}
