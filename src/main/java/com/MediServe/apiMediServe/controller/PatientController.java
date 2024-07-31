package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }
}
