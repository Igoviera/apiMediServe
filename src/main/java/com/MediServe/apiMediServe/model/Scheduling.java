package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.MediServe.apiMediServe.enums.SchedulingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scheduling")
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek day;

    private LocalTime consultationTime;

    @Enumerated(EnumType.STRING)
    private SchedulingStatus schedulingStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}
