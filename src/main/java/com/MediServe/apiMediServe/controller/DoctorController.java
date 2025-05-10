package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.doctor.DoctorDTO;
import com.MediServe.apiMediServe.dto.doctor.DoctorResponseDTO;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mediServe/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping()
    @Operation(
            summary = "Cadastrar médico",
            description = "Cadastra um médico no banco de dados.",
            tags = "doctor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO createDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        return doctorService.createDoctor(doctorDTO);
    }

    @GetMapping
    @Operation(
            summary = "Listar médicos",
            description = "Retorna uma lista paginada de médicos, ordenada por nome (10 por página).",
            tags = "doctor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de médicos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<DoctorResponseDTO> getAllDoctor(@PageableDefault(size = 10, sort = "name,asc") Pageable pageable){
        return doctorService.getAllDoctor(pageable);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Obter médico por ID",
            description = "Retorna os dados de um médico com base no ID informado.",
            tags = "doctor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public DoctorResponseDTO getByIdDoctor(@PathVariable("id") Long id){
        return doctorService.getByIdDoctor(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar médico por ID",
            description = "Atualiza os dados de um médico com base no ID informado.",
            tags = "doctor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Médico não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public DoctorDTO updateDoctor(@Valid @PathVariable("id") Long id, @RequestBody DoctorDTO doctorDTO){
        return doctorService.updateDoctor(id, doctorDTO);
    }
}
