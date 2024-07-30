package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    List<Doctor> getAllDoctor();
}
