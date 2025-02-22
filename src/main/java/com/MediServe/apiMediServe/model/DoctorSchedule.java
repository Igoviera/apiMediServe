package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    //private int limitPatients;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Doctor doctor;

}

