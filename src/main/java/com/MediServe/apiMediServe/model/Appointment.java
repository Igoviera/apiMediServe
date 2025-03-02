package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ID do paciente é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patientId;

    @NotNull(message = "O ID do médico é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctorId;

    @NotNull(message = "O ID da clinica é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinicId;

    @FutureOrPresent(message = "A data da consulta deve estar no presente ou no futuro.")
    private LocalDateTime appointmentDateTime;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da consulta é obrigatório.")
    private StatusAppointment statusAppointment ;

}
