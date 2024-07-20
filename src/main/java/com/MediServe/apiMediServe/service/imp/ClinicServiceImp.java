package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImp implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Clinic createClinic(Clinic clinic) {
        return null;
    }

    @Override
    public List<Clinic> findAllClinic() {
        return clinicRepository.findAll();
    }

    @Override
    public Clinic findBayIdClinic(Long id) {
        return null;
    }

    @Override
    public Clinic updateClinic(Clinic clinic) {
        return null;
    }
}
