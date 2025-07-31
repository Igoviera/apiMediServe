package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.appointment.AppointmentRequestDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDetailDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO);
    void cancelAppointment(Long id);
    List<AppointmentResponseDTO> findAll();
    AppointmentResponseDTO findById(Long id);
}
