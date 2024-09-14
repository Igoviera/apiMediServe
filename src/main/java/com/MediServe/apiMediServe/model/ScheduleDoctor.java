package com.MediServe.apiMediServe.model;

import com.MediServe.apiMediServe.enums.DayOfTheWeek;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class ScheduleDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
//    @NotEmpty(message = "O dia é obrigatório")
    private DayOfTheWeek dayOfWeek;
    @NotNull(message = "A hora de inicio é obrigatória")
    private LocalTime startTime;
    @NotNull(message = "A hora de encerramento é obrigatória")
    private LocalTime endTime;
    //private int limitPatients;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Doctor doctor;

}

