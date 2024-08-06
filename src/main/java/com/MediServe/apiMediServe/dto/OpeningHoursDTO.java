package com.MediServe.apiMediServe.dto;

import com.MediServe.apiMediServe.enums.DiaDaSemana;
import com.MediServe.apiMediServe.model.Doctor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalTime;

public record OpeningHoursDTO(
        Long id,
        DiaDaSemana dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Long doctorId
) {}
