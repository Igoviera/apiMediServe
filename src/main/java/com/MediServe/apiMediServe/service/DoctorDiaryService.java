package com.MediServe.apiMediServe.service;

import com.MediServe.apiMediServe.dto.doctorDiary.AvailableTimesDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryRequestDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryResponseDTO;
import com.MediServe.apiMediServe.model.DoctorDiary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface DoctorDiaryService {
    List<LocalTime> gerarHorariosDisponiveis(Long id);

    List<AvailableTimesDTO> getDaySchedule(Long id);
    DoctorDiaryResponseDTO buscarPorId(Long id);

    DoctorDiaryResponseDTO save(DoctorDiaryRequestDTO diary);

    List<DoctorDiaryResponseDTO> findAll();

    List<DoctorDiaryResponseDTO> BuscarAgendaPorMedico(Long id);
}
