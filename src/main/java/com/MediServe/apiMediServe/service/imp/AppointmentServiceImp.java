package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.appointment.AppointmentRequestDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.dto.appointment.AppointmentResponseDTO;
import com.MediServe.apiMediServe.exception.AppointmentConflictException;
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

        Clinic clinic = clinicRepository.findById(appointmentRequestDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException("Clinica não encontrada com o id: " + appointmentRequestDTO.clinicId()));

        Doctor doctor = doctorRepository.findById(appointmentRequestDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com o id: " + appointmentRequestDTO.doctorId()));

        Patient patient = patientRepository.findById(appointmentRequestDTO.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado com id: " + appointmentRequestDTO.patientId()));


        validateAppointmentTime(appointmentRequestDTO, clinic); // agora com horário da clínica
        validateExistsAppointment(appointmentRequestDTO, doctor);
        validateDoctorAvailability(appointmentRequestDTO, doctor);

        Appointment appointment = appointmentMapper.toEntity(appointmentRequestDTO, clinic, doctor, patient);

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));

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

    public void validateAppointmentTime(AppointmentRequestDTO appointmentRequestDTO, Clinic clinic) {
        var day = appointmentRequestDTO.dayOfWeek();
        var time = appointmentRequestDTO.time();

        boolean isSunday = day.equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = time.isBefore(clinic.getOpeningTime());
        boolean afterClosing = time.isAfter(clinic.getClosingTime());

        if (isSunday || beforeOpening || afterClosing) {
            throw new AppointmentConflictException(
                    String.format("A consulta deve ser agendada entre %s e %s, de segunda a sábado.",
                            clinic.getOpeningTime(), clinic.getClosingTime())
            );
        }
    }

    public void validateExistsAppointment(AppointmentRequestDTO dto, Doctor doctor) {
        boolean exists = appointmentRepository.existsByDoctorAndDayOfWeekAndTime(doctor, dto.dayOfWeek(), dto.time());

        if (exists) {
            throw new AppointmentConflictException("O médico já possui uma consulta agendada na " + dto.dayOfWeek() + " às " + dto.time() + ".");
        }
    }

    private void validateDoctorAvailability(AppointmentRequestDTO dto, Doctor doctor) {
        DayOfWeek day = dto.dayOfWeek();
        LocalTime time = dto.time();

        doctor.getDiaries().stream()
                .filter(d -> d.getDayOfWeek().equals(day))
                .filter(d -> {
                    boolean dentroDoHorario = !time.isBefore(d.getStartTime()) &&
                            time.plusMinutes(d.getDurationMinutes()).isBefore(d.getEndTime());

                    boolean alinhadoComDuracao = Duration.between(d.getStartTime(), time).toMinutes() % d.getDurationMinutes() == 0;

                    return dentroDoHorario && alinhadoComDuracao;
                })
                .findFirst()
                .orElseThrow(() ->
                    new AppointmentConflictException("O médico não atende na " + day + " nesse horário: " + time)
                );

    }
}
