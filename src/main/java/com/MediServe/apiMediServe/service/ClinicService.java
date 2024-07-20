package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.model.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic createClinic(Clinic clinic);
    List<Clinic> findAllClinic();
    Clinic findBayIdClinic(Long id);
    Clinic updateClinic(Clinic clinic);
}
