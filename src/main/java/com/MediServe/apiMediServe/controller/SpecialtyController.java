package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.specialty.SpecialtyDTO;
import com.MediServe.apiMediServe.service.SpecialtyService;
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
@RequestMapping("/api/mediServe/specialtys")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @PostMapping
    @Operation(
            summary = "Cadastrar especialidade",
            description = "Cadastra uma nova especialidade médica no sistema.",
            tags = "specialty"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Especialidade cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyDTO createSpecialty(@Valid @RequestBody SpecialtyDTO specialtyDTO){
        return specialtyService.createSpecialty(specialtyDTO);
    }
    @GetMapping
    @Operation(
            summary = "Listar todas as especialidades",
            description = "Retorna uma lista com todas as especialidades médicas cadastradas.",
            tags = "specialty"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<SpecialtyDTO> getAllSpecialty(){
        return specialtyService.getAllSpecialty();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obter especialidade por ID",
            description = "Retorna os dados de uma especialidade médica com base no ID fornecido.",
            tags = "specialty"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Especialidade não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public SpecialtyDTO getByIdSpecialty(@PathVariable("id") Long id){
        return specialtyService.getByIdSpecialty(id);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar especialidade por ID",
            description = "Atualiza os dados de uma especialidade médica com base no ID fornecido.",
            tags = "specialty"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Especialidade atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Especialidade não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public SpecialtyDTO updateSpecialty(@PathVariable("id") Long id, @RequestBody SpecialtyDTO specialtyDTO){
         return specialtyService.updateSpecialty(id, specialtyDTO);
    }
}
