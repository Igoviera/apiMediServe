package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.patient.PatientRequestDTO;
import com.MediServe.apiMediServe.dto.patient.PatientResponseDTO;
import com.MediServe.apiMediServe.service.PatientService;
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
@RequestMapping("/api/mediServe/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    @Operation(
            summary = "Listar todos os pacientes",
            description = "Retorna uma lista com todos os pacientes cadastrados.",
            tags = "patient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<PatientResponseDTO> findAllPatients(){
        return patientService.findAllPatients();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Cadastrar paciente",
            description = "Cadastra um novo paciente no sistema.",
            tags = "patient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public PatientResponseDTO createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return patientService.createPatient(patientRequestDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar paciente por ID",
            description = "Retorna os dados de um paciente com base no ID fornecido.",
            tags = "patient"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public PatientResponseDTO findByPatientId(@PathVariable("id") Long id){
       return patientService.findByPatientId(id);
    }
}
