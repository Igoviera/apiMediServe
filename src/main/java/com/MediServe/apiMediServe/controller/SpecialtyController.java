package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.model.Specialty;
import com.MediServe.apiMediServe.service.SpecialtyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/specialtys")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Specialty createSpecialty(@Valid @RequestBody Specialty specialty){
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
    @PutMapping("/{id}")
    public Specialty updateSpecialty(@PathVariable("id") Long id, @RequestBody Specialty specialty){
         return specialtyService.updateSpecialty(id, specialty);
    }
}
