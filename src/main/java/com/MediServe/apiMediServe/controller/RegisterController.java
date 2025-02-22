package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.DoctorDTO;
import com.MediServe.apiMediServe.dto.DoctorRegistrationDTO;
import com.MediServe.apiMediServe.dto.PatientDTO;
import com.MediServe.apiMediServe.dto.PatientRegistrationDTO;
import com.MediServe.apiMediServe.service.RegisterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/mediServe/register")
public class RegisterController {
//
//    private final RegisterService registerService;
//
//    @PostMapping("/doctor")
//    @ResponseStatus(HttpStatus.CREATED)
//    public DoctorDTO registerDoctor(@Valid @RequestBody DoctorRegistrationDTO doctorRegistrationDTO){
//        return registerService.registerDoctor(doctorRegistrationDTO);
//    }
//    @PostMapping("/patient")
//    @ResponseStatus(HttpStatus.CREATED)
//    public PatientDTO registerPatient(@RequestBody PatientRegistrationDTO patientRegistrationDTO){
//        return registerService.registerPatient(patientRegistrationDTO);
//    }
}
