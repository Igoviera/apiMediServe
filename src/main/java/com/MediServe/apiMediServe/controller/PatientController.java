package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.patient.PatientDTO;
import com.MediServe.apiMediServe.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public PatientDTO createPatient(@Valid PatientDTO patientDTO){
        return patientService.createPatient(patientDTO);
    }

    @GetMapping("/{id}")
    public PatientDTO findByPatientId(@PathVariable("id") Long id){
        patientService.findByPatientId(id);
    }
}
