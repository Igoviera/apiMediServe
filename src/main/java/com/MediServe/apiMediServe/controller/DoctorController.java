package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO createDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        return doctorService.createDoctor(doctorDTO);
    }

    @GetMapping
    public Page<DoctorResponseDTO> getAllDoctor(@PageableDefault(size = 10, sort = "name,asc") Pageable pageable){
        return doctorService.getAllDoctor(pageable);
    }

    @GetMapping("/{id}")
    public DoctorResponseDTO getByIdDoctor(@PathVariable("id") Long id){
        return doctorService.getByIdDoctor(id);
    }

    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@Valid @PathVariable("id") Long id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.updateDoctor(id, doctorDTO);
    }
}
