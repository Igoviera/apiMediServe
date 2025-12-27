package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.DoctorAvailabilityResponseDTO;

import java.time.LocalDate;

public interface DoctorAvailabilityService {
    DoctorAvailabilityResponseDTO getAvailability(Long doctorId, LocalDate date);
}
