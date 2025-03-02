package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO createScheduling(@Valid @RequestBody AppointmentDTO appointmentDTO){
        return appointmentService.createAppointment(appointmentDTO);
    }
}