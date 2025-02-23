package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.specialty.SpecialtyDTO;
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
    public SpecialtyDTO createSpecialty(@Valid @RequestBody SpecialtyDTO specialtyDTO){
        return specialtyService.createSpecialty(specialtyDTO);
    }
    @GetMapping
    public List<SpecialtyDTO> getAllSpecialty(){
        return specialtyService.getAllSpecialty();
    }

    @GetMapping("/{id}")
    public SpecialtyDTO getByIdSpecialty(@PathVariable("id") Long id){
        return specialtyService.getByIdSpecialty(id);
    }
    @PutMapping("/{id}")
    public SpecialtyDTO updateSpecialty(@PathVariable("id") Long id, @RequestBody SpecialtyDTO specialtyDTO){
         return specialtyService.updateSpecialty(id, specialtyDTO);
    }
}
