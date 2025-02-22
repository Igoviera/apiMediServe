package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.AppointmentDTO;
import com.MediServe.apiMediServe.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/scheduling")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO createScheduling(@RequestBody AppointmentDTO appointmentDTO){
        return appointmentService.createScheduling(appointmentDTO);
    }
}