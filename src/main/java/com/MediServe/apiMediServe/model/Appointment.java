package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.StatusAppointment;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private LocalTime time;

    @NotNull(message = "O ID do paciente é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull(message = "O ID do médico é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull(message = "O ID da clinica é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da consulta é obrigatório.")
    private StatusAppointment statusAppointment ;

}
