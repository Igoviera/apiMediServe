package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.DoctorAvailabilityResponseDTO;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.model.DoctorDiary;
import com.MediServe.apiMediServe.repository.AppointmentRepository;
import com.MediServe.apiMediServe.repository.DoctorDiaryRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.service.DoctorAvailabilityService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DoctorAvailabilityServiceImp implements DoctorAvailabilityService {

    private final DoctorDiaryRepository diaryRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public DoctorAvailabilityServiceImp(
            DoctorDiaryRepository diaryRepository,
            AppointmentRepository appointmentRepository, DoctorRepository doctorRepository
    ) {
        this.diaryRepository = diaryRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorAvailabilityResponseDTO getAvailability(Long doctorId, LocalDate date) {
        doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado"));

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        DoctorDiary diary = diaryRepository.findByDoctorIdAndDayOfWeek(doctorId, dayOfWeek)
                .orElseThrow(() -> new RecordNotFoundException("Médico não possui agenda configurada"));

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Appointment> appointments =
                appointmentRepository.findByDoctorIdAndDateBetween(
                        doctorId,
                        startOfDay,
                        endOfDay
                );

        Set<LocalTime> occupiedTimes = appointments.stream()
                .map(a -> a.getDate().toLocalTime())
                .collect(Collectors.toSet());

        List<LocalTime> availableSlots = new ArrayList<>();

        LocalTime pointer = diary.getStartTime();
        LocalTime endTime = diary.getEndTime();

        while (!pointer.isAfter(diary.getEndTime().minusMinutes(diary.getDurationMinutes()))) {
            if (!occupiedTimes.contains(pointer)) {
                availableSlots.add(pointer);
            }
            pointer = pointer.plusMinutes(diary.getDurationMinutes());
        }

        return new DoctorAvailabilityResponseDTO(
                doctorId,
                date,
                diary.getDurationMinutes(),
                availableSlots
        );
    }
}
