package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> findAllPatients(){
        return patientService.findAllPatients();
    }
}
