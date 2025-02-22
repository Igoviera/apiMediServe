package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.SchedulingStatus;
import jakarta.persistence.*;
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
@Table(name = "scheduling")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus schedulingStatus;
}
