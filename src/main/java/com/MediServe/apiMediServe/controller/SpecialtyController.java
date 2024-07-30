package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/specialty")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @PostMapping
    public Specialty createSpecialty(@RequestBody Specialty specialty){
        return specialtyService.createSpecialty(specialty);
    }
    @GetMapping
    public List<Specialty> getAllSpecialty(){
        return specialtyService.getAllSpecialty();
    }

    @GetMapping("/{id}")
    public Specialty getByIdSpecialty(@PathVariable("id") Long id){
        return specialtyService.getByIdSpecialty(id);
    }
}
