package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppointmentMapper {

    public AppointmentResponseDTO toDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getClinic().getName(),
                appointment.getDoctor().getName(),
                appointment.getPatient().getName(),
                appointment.getDate(),
                appointment.getStatusAppointment()
        );
    }

    public Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO, Clinic clinic, Doctor doctor, Patient patient) {

        if (appointmentRequestDTO == null) {
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setClinic(clinic);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDate(appointmentRequestDTO.date());

        return appointment;
    }
}
