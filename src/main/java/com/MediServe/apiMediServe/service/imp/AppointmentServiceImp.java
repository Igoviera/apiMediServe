package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.appointment.AppointmentRequestDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.dto.appointment.AppointmentResponseDTO;
import com.MediServe.apiMediServe.enums.StatusAppointment;
import com.MediServe.apiMediServe.exception.AppointmentConflictException;
import com.MediServe.apiMediServe.exception.BusinessException;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.*;
import com.MediServe.apiMediServe.repository.*;
import com.MediServe.apiMediServe.service.AppointmentService;
import com.MediServe.apiMediServe.service.DoctorDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.IllegalArgumentException; // <-- CORRETA
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    AppointmentMapper appointmentMapper;

    @Autowired
    DoctorDiaryService doctorDiaryService;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        if (appointmentRequestDTO.date().isBefore(LocalDateTime.now())){
            throw new BusinessException("Data e hora inválidas para agendamento");
        }
        Clinic clinic = clinicRepository.findById(appointmentRequestDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException("Clinica não encontrada com o id: " + appointmentRequestDTO.clinicId()));

        Doctor doctor = doctorRepository.findById(appointmentRequestDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com o id: " + appointmentRequestDTO.doctorId()));

        Patient patient = patientRepository.findById(appointmentRequestDTO.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado com id: " + appointmentRequestDTO.patientId()));


        validateAppointmentTime(appointmentRequestDTO, clinic);
        validateExistsAppointment(appointmentRequestDTO, doctor);
        validateDoctorAvailability(appointmentRequestDTO, doctor);

        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO, clinic, doctor, patient);
        appointment.setStatusAppointment(StatusAppointment.AGENDADA);

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));

    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Agendamendo não encontrado com id: " + id));

        if (StatusAppointment.CANCELADA.equals(appointment.getStatusAppointment())){
            throw new AppointmentConflictException("Consulta já foi cancelada.");
        }

        appointment.setStatusAppointment(StatusAppointment.CANCELADA);

        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentMapper.toDTO(appointment))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta não encontrada com id: " + id));

        return appointmentMapper.toDTO(appointment);
    }

    @Override
    public void finishAppoitment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta não encontrada com id: " + id));

        if (appointment.getStatusAppointment() == StatusAppointment.CONCLUIDA){
            throw new BusinessException("Essa consulta já foi finalizada");
        }

        appointment.setStatusAppointment(StatusAppointment.CONCLUIDA);
        appointmentRepository.save(appointment);
    }

    public void validateAppointmentTime(AppointmentRequestDTO appointmentRequestDTO, Clinic clinic) {
        var dateTime = appointmentRequestDTO.date();
        var day = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();
        int minutes = time.getMinute();

        boolean isSunday = day.equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = time.isBefore(clinic.getOpeningTime());
        boolean afterClosing = time.isAfter(clinic.getClosingTime());

        if (isSunday || beforeOpening || afterClosing) {
            throw new AppointmentConflictException(
                    String.format("A consulta deve ser agendada entre %s e %s, de segunda a sábado.",
                            clinic.getOpeningTime(), clinic.getClosingTime())
            );
        }
        if (minutes != 0 && minutes != 30){
            throw new AppointmentConflictException("A consulta deve ser marcada em horários com minutos 00 ou 30.");
        }
    }

    public void validateExistsAppointment(AppointmentRequestDTO dto, Doctor doctor) {
        LocalDateTime date = dto.date();

        boolean exists = appointmentRepository.existsByDoctorAndDate(doctor, date);

        if (exists){
            throw new AppointmentConflictException(
                    String.format("O médico já possui uma consulta agendada para: %s", date)
            );
        }
    }

    private String formatDayofWeek(DayOfWeek day) {
        return switch (day) {
            case MONDAY -> "segunda-feira";
            case TUESDAY -> "terça-feira";
            case WEDNESDAY -> "quarta-feira";
            case THURSDAY -> "quinta-feira";
            case FRIDAY -> "sexta-feira";
            case SATURDAY -> "sábado";
            case SUNDAY -> "domingo";
        };
    }

    private void validateDoctorAvailability(AppointmentRequestDTO dto, Doctor doctor) {
        DayOfWeek day = dto.date().getDayOfWeek();
        LocalTime time = dto.date().toLocalTime();

        System.out.println(">> Validando disponibilidade do médico...");
        System.out.println("Dia: " + day);
        System.out.println("Hora da consulta: " + time);

        doctor.getDiaries().forEach(diary -> {
            System.out.println("---- Diário encontrado ----");
            System.out.println("Dia do diário: " + diary.getDayOfWeek());
            System.out.println("Início: " + diary.getStartTime());
            System.out.println("Fim: " + diary.getEndTime());
            System.out.println("Duração: " + diary.getDurationMinutes());

            boolean dentroDoHorario = !time.isBefore(diary.getStartTime()) &&
                    !time.plusMinutes(diary.getDurationMinutes()).isAfter(diary.getEndTime());

            boolean alinhadoComDuracao = Duration.between(diary.getStartTime(), time).toMinutes() % diary.getDurationMinutes() == 0;

            System.out.println("Está dentro do horário? " + dentroDoHorario);
            System.out.println("Está alinhado com a duração? " + alinhadoComDuracao);
        });

        doctor.getDiaries().stream()
                .filter(d -> d.getDayOfWeek().equals(day))
                .filter(d -> {
                    boolean dentroDoHorario = !time.isBefore(d.getStartTime()) &&
                            !time.plusMinutes(d.getDurationMinutes()).isAfter(d.getEndTime());

                    boolean alinhadoComDuracao = Duration.between(d.getStartTime(), time).toMinutes() % d.getDurationMinutes() == 0;

                    return dentroDoHorario && alinhadoComDuracao;
                })
                .findFirst()
                .orElseThrow(() ->
                        new AppointmentConflictException(String.format(
                                "O médico não atende na %s nesse horário: %s", formatDayofWeek(day), time))
                );
    }


}
