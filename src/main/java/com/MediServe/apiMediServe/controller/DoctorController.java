package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO createDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        return doctorService.createDoctor(doctorDTO);
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctor(){
        return doctorService.getAllDoctor();
    }

    @GetMapping("/{id}")
    public DoctorDTO getByIdDoctor(@PathVariable("id") Long id){
        return doctorService.getByIdDoctor(id);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@Valid @PathVariable("id") Long id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.updateDoctor(id, doctorDTO);
    }
}
