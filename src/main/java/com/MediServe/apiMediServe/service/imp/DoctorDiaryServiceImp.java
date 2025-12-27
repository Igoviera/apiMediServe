package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.doctorDiary.AvailableTimesDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryMapper;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryRequestDTO;
import com.MediServe.apiMediServe.dto.doctorDiary.DoctorDiaryResponseDTO;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.DoctorDiary;
import com.MediServe.apiMediServe.repository.AppointmentRepository;
import com.MediServe.apiMediServe.repository.DoctorDiaryRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.service.DoctorDiaryService;
import com.MediServe.apiMediServe.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorDiaryServiceImp implements DoctorDiaryService {

    private final DoctorDiaryRepository diaryRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorDiaryMapper doctorDiaryMapper;

    public DoctorDiaryServiceImp(DoctorDiaryRepository diaryRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, DoctorDiaryMapper doctorDiaryMapper) {
        this.diaryRepository = diaryRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.doctorDiaryMapper = doctorDiaryMapper;
    }

//    @Override
//    public List<LocalTime> gerarHorariosDisponiveis(Long id) {
//        DoctorDiary diary = diaryRepository.findById(id)
//                .orElseThrow(() -> new RecordNotFoundException("Agenda não encontrada com id: " + id));
//
//        DayOfWeek dia = diary.getDayOfWeek();
//
//        List<Appointment> appointments = appointmentRepository.findByDoctor(diary.getDoctor());
//
//        Set<LocalTime> horariosOcupados = appointments.stream()
//                .filter(appointment -> appointment.getDate().getDayOfWeek().equals(dia))
//                .map(appointment -> appointment.getDate().toLocalTime())
//                .collect(Collectors.toSet());
//
//        // Gera horarios disponiveis
//        LocalTime startTime = diary.getStartTime();
//        LocalTime endTime = diary.getEndTime();
//        int durationMinutes = diary.getDurationMinutes();
//
//        List<LocalTime> horariosDisponiveis = new ArrayList<>();
//
//        while (startTime.plusMinutes(durationMinutes).compareTo(endTime) <= 0) {
//            if (!horariosOcupados.contains(startTime)) {
//                horariosDisponiveis.add(startTime);
//            }
//            startTime = startTime.plusMinutes(durationMinutes);
//        }
//
//        System.out.println(horariosDisponiveis);
//
//        return horariosDisponiveis;
//    }

//    public List<AvailableTimesDTO> getDaySchedule(Long doctorId) {
//        List<DoctorDiary> diaries = diaryRepository.findByDoctorId(doctorId);
//
//        if (diaries.isEmpty()) {
//            throw new RecordNotFoundException("Nenhuma agenda encontrada para o médico com id: " + doctorId);
//        }
//
//        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
//
//        return diaries.stream().map(diary -> {
//            DayOfWeek dayOfWeek = diary.getDayOfWeek();
//
//            Set<LocalTime> horariosOcupados = appointments.stream()
//                    .filter(appt -> appt.getDate().getDayOfWeek().equals(dayOfWeek))
//                    .map(appt -> appt.getDate().toLocalTime())
//                    .collect(Collectors.toSet());
//
//            LocalTime startTime = diary.getStartTime();
//            LocalTime endTime = diary.getEndTime();
//            int durationMinutes = diary.getDurationMinutes();
//
//            List<String> timeSlots = new ArrayList<>();
//            LocalTime timePointer = startTime;
//
//            while (!timePointer.isAfter(endTime.minusMinutes(durationMinutes))) {
//                if (!horariosOcupados.contains(timePointer)) {
//                    timeSlots.add(timePointer.toString()); // formato HH:mm:ss
//                }
//                timePointer = timePointer.plusMinutes(durationMinutes);
//            }
//
//            String dayLabel = DateUtils.getDayLabel(dayOfWeek);
//
//            return new AvailableTimesDTO(dayLabel, timeSlots);
//        }).collect(Collectors.toList());
//    }


    @Override
    public DoctorDiaryResponseDTO buscarPorId(Long id) {
        return doctorDiaryMapper.toDTO(
                diaryRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException("Diario não ncontrado com id: " + id)));
    }

    @Override
    public DoctorDiaryResponseDTO save(DoctorDiaryRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com id: " + dto.doctorId()));

        DayOfWeek dayOfWeek = DoctorDiaryMapper.fromPortuguese(dto.dayOfWeek());

        Optional<DoctorDiary> existing = diaryRepository.findByDoctorIdAndDayOfWeek(dto.doctorId(), dayOfWeek);
        if (existing.isPresent()) {
            throw new IllegalArgumentException("já existe uma agenda para esse médico no dia " + dayOfWeek);
        }

        return doctorDiaryMapper.toDTO(diaryRepository.save(doctorDiaryMapper.toEntity(dto, doctor)));
    }

    @Override
    public List<DoctorDiaryResponseDTO> findAll() {
        return diaryRepository.findAll().stream().map(doctorDiary -> doctorDiaryMapper.toDTO(doctorDiary)).collect(Collectors.toList());
    }

    @Override
    public List<DoctorDiaryResponseDTO> BuscarAgendaPorMedico(Long id) {
        return diaryRepository.findByDoctorId(id).stream().map(doctorDiary -> doctorDiaryMapper.toDTO(doctorDiary)).collect(Collectors.toList());
    }
}
