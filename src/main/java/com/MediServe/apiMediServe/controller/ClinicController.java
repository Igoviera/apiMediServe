package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.service.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/clinics")
public class ClinicController {

    private final ClinicService clinicService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicDTO createClinic(@Valid @RequestBody ClinicDTO clinicDTO){
        return clinicService.createClinic(clinicDTO);
    }
    @GetMapping
    public List<ClinicDTO> findAllClinic(){
        System.out.println();
        return clinicService.findAllClinic();
    }
    @GetMapping("/{id}")
    public ClinicDTO findByIdClinic(@PathVariable("id") Long id){
        return  clinicService.findByIdClinic(id);
    }
    @PutMapping("/{clinicId}")
    public ClinicDTO updateClinic(@Valid @PathVariable("clinicId") Long clinicId, @RequestBody ClinicDTO clinicDTO){
        return clinicService.updateClinic(clinicId, clinicDTO);
    }
}
