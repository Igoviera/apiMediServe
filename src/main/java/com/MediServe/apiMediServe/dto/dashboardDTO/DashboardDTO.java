package com.MediServe.apiMediServe.dto.dashboardDTO;

public record DashboardDTO(
        long totalPatient,
        long totalDoctors,
        long totalAppointment,
        long totalAppointmentCancel
) {
}
