package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.dashboardDTO.DashboardDTO;
import com.MediServe.apiMediServe.repository.AppointmentRepository;
import com.MediServe.apiMediServe.repository.DoctorDiaryRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.service.AppointmentService;
import com.MediServe.apiMediServe.service.DashboardService;
import com.MediServe.apiMediServe.service.DoctorService;
import com.MediServe.apiMediServe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImp implements DashboardService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DashboardServiceImp(PatientRepository patientRepository, DoctorDiaryRepository doctorDiaryRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public DashboardDTO getDashboardData() {
        long totalPatient = patientRepository.count();
        long totalDoctor = doctorRepository.count();
        long totalAppointment = appointmentRepository.count();
        return new DashboardDTO(totalPatient, totalDoctor, totalAppointment);
    }
}
