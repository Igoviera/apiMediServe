package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mediServe/Clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @GetMapping
    public List<Clinic> findAllClinic(){
        return clinicService.findAllClinic();
    }

}
