package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.DoctorDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> getAllDoctor(){
        return doctorService.getAllDoctor();
    }
    @GetMapping("/{id}")
    public Doctor getByIdDoctor(@PathVariable("id") Long id){
        return doctorService.getByIdDoctor(id);
    }
    @PutMapping("/{id}")
    public Doctor updateDoctor(@Valid @PathVariable("id") Long id, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(id, doctor);
    }
}
