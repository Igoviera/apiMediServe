package com.MediServe.apiMediServe.dto.appointment;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.model.Appointment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppointmentMapper {

    public AppointmentDTO toDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return new AppointmentDTO(
                appointment.getId(),
                appointment.getClinicId().getId(),
                appointment.getDoctorId().getId(),
                appointment.getPatientId().getId(),
                appointment.getAppointmentDateTime(),
                appointment.getStatusAppointment()
        );
    }

    public AppointmentDetailDTO toDetailDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return new AppointmentDetailDTO(
                appointment.getId(),
                appointment.getPatientId().getName(),
                appointment.getDoctorId().getName(),
                appointment.getAppointmentDateTime(),
                appointment.getStatusAppointment()
        );
    }

    public Appointment toEntity(AppointmentDTO appointmentDTO) {

        if (appointmentDTO == null) {
            return null;
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentDTO.data());
        appointment.setStatusAppointment(appointmentDTO.status());

        return appointment;
    }
}
