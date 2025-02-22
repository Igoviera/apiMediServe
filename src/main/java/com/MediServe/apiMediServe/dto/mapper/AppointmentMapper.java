package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.AppointmentDTO;
import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppointmentMapper {
    private final ClinicRepository clinicRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;

    public AppointmentDTO toDTO(Appointment appointment){
        if (appointment == null){
            return null;
        }

        return new AppointmentDTO(
                appointment.getId(),
                appointment.getAppointmentDateTime(),
                appointment.getSchedulingStatus(),
                appointment.getPatient().getId(),
                appointment.getDoctor().getId(),
                appointment.getClinic().getId()
        );
    }

    public Appointment toEntity(AppointmentDTO appointmentDTO){
        if (appointmentDTO == null){
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentDTO.appointmentDateTime());
//        Patient patient = patientRepository.findById(schedulingDTO.patientId())
//                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id " + schedulingDTO.patientId()));
//
//        Doctor doctor = doctorRepository.findById(schedulingDTO.doctorId())
//                .orElseThrow(() -> new RuntimeException("Médico não encontrado com id " + schedulingDTO.doctorId()));
//
//        Clinic clinic = clinicRepository.findById(schedulingDTO.clinicId())
//                .orElseThrow(() -> new RuntimeException("Clínica não encontrada com id " + schedulingDTO.clinicId()));

        return appointment;
    }
}
