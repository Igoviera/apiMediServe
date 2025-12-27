package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.appointment.AppointmentRequestDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDetailDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentResponseDTO;
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
    public AppointmentResponseDTO createScheduling(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return appointmentService.createAppointment(appointmentRequestDTO);
    }

    @PostMapping("/{id}/finish")
    public void finishAppoitment(@PathVariable Long id){
        appointmentService.finishAppoitment(id);
    }

    @PostMapping("/{id}/cancel")
    public void cancelAppointment(@PathVariable("id") Long id){
        appointmentService.cancelAppointment(id);
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
    public List<AppointmentResponseDTO> findAll() {
        return appointmentService.findAll();
    }
    @GetMapping("/{id}")
    public AppointmentResponseDTO findById(@PathVariable("id") Long id){
        return appointmentService.findById(id);
    }


//    @GetMapping("/available")
//    public List<LocalTime> getAvailableSlots(@RequestParam Long doctorId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        return appointmentService.getAvailableSlots(doctorId, date);
//
//    }
}