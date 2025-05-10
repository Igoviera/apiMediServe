package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDetailDTO;
import com.MediServe.apiMediServe.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Agendar consulta",
            description = "Cria um novo agendamento de consulta com base nos dados fornecidos.",
            tags = "appointment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consulta agendada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para agendamento"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public AppointmentDTO createScheduling(@Valid @RequestBody AppointmentDTO appointmentDTO){
        return appointmentService.createAppointment(appointmentDTO);
    }

    @GetMapping
    @Operation(
            summary = "Listar agendamentos",
            description = "Retorna todos os agendamentos de consultas registrados no sistema.",
            tags = "appointment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamentos retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<AppointmentDetailDTO> findAll(){
        return appointmentService.findAll();
    }
}