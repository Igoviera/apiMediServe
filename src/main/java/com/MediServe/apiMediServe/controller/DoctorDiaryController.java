package com.MediServe.apiMediServe.controller;

import com.MediServe.apiMediServe.dto.doctorDiary.AvailableTimesDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryMapper;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryRequestDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryResponseDTO;
import com.MediServe.apiMediServe.model.DoctorDiary;
import com.MediServe.apiMediServe.service.DoctorDiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping("/{id}/schedule")
//    public ResponseEntity<List<AvailableTimesDTO>> getDoctorDiarySchedule(@PathVariable Long id) {
//        List<AvailableTimesDTO> schedule = doctorDiaryService.getDaySchedule(id);
//        return ResponseEntity.ok(schedule);
//    }


    @GetMapping("/{id}")
    public List<DoctorDiaryResponseDTO> BuscarAgendaPorMedico(@PathVariable Long id){
        return doctorDiaryService.BuscarAgendaPorMedico(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDiaryResponseDTO criar(@Valid @RequestBody DoctorDiaryRequestDTO diary) {
        return doctorDiaryService.save(diary);
    }

//    @GetMapping("/{id}/horarios")
//    public List<LocalTime> getHorarioDisponiveis(@PathVariable Long id){
//      return doctorDiaryService.gerarHorariosDisponiveis(id);
//    }
}
