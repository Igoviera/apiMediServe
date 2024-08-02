package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
    @GetMapping
    public List<Doctor> getAllDoctor(){
        return doctorService.getAllDoctor();
    }
    @GetMapping("/{id}")
    public Doctor getByIdDoctor(@PathVariable("id") Long id){
        return doctorService.getByIdDoctor(id);
    }
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable("id") Long id, @RequestBody Doctor doctor){
        return doctorService.updateDoctor(id, doctor);
    }
}
