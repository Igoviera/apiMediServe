package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDetailDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDetailDTO> findAll();
    AppointmentDTO findById(Long id);
}
