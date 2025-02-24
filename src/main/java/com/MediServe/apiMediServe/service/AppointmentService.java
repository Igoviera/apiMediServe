package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.AppointmentDTO;

public interface AppointmentService {
    AppointmentDTO createScheduling(AppointmentDTO appointmentDTO);
    AppointmentDTO findByIdAppoitment(Long id);
}
