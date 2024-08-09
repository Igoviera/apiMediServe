package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.SchedulingDTO;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.Scheduling;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SchedulingMapper {
    private final ClinicRepository clinicRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;

    public SchedulingDTO toDTO(Scheduling scheduling){
        if (scheduling == null){
            return null;
        }

        return new SchedulingDTO(
                scheduling.getId(),
                scheduling.getDay(),
                scheduling.getConsultationTime(),
                scheduling.getSchedulingStatus(),
                scheduling.getPatient().getId(),
                scheduling.getDoctor().getId(),
                scheduling.getClinic().getId()
        );
    }

    public Scheduling toEntity(SchedulingDTO schedulingDTO){
        if (schedulingDTO == null){
            return null;
        }

        Scheduling scheduling = new Scheduling();
        scheduling.setDay(schedulingDTO.day());
        scheduling.setConsultationTime(schedulingDTO.consultationTime());
//        Patient patient = patientRepository.findById(schedulingDTO.patientId())
//                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id " + schedulingDTO.patientId()));
//
//        Doctor doctor = doctorRepository.findById(schedulingDTO.doctorId())
//                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id " + schedulingDTO.doctorId()));
//
//        Clinic clinic = clinicRepository.findById(schedulingDTO.clinicId())
//                .orElseThrow(() -> new RuntimeException("Clínica não encontrada com id " + schedulingDTO.clinicId()));

        return scheduling;
    }
}
