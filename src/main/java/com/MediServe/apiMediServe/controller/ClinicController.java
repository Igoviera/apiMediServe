package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.service.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/Clinic")
public class ClinicController {

    private final ClinicService clinicService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Clinic createClinic(@RequestBody @Valid Clinic clinic){
        return clinicService.createClinic(clinic);
    }
    @GetMapping
    public List<ClinicDTO> findAllClinic(){
        return clinicService.findAllClinic();
    }

    @GetMapping("/{id}")
    public Clinic findByIdClinic(@PathVariable("id") Long id){
        return  clinicService.findByIdClinic(id);
    }

    @PutMapping("/{clinicId}")
    public Clinic updateClinic(@PathVariable("clinicId") Long clinicId, Clinic clinic){
        return clinicService.updateClinic(clinicId, clinic);
    }
}
