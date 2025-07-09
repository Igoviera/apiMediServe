package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryMapper;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryRequestDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryResponseDTO;
import com.MediServe.apiMediServe.model.DoctorDiary;
import com.MediServe.apiMediServe.service.DoctorDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mediServe/doctor-diary")
public class DoctorDiaryController {

    private final DoctorDiaryService doctorDiaryService;
    private  final DoctorDiaryMapper doctorDiaryMapper;

    public DoctorDiaryController(DoctorDiaryService diaryService, DoctorDiaryMapper doctorDiaryMapper) {
        this.doctorDiaryService = diaryService;
        this.doctorDiaryMapper = doctorDiaryMapper;
    }

    @GetMapping
    public List<DoctorDiaryResponseDTO> listarTodos() {
        return doctorDiaryService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDiaryResponseDTO criar(@RequestBody DoctorDiaryRequestDTO diary) {
        return doctorDiaryService.save(diary);
    }

    @GetMapping("/{id}/horarios")
    public List<LocalTime> getHorarioDisponiveis(@PathVariable Long id){
      return doctorDiaryService.gerarHorariosDisponiveis(id);
    }
}
