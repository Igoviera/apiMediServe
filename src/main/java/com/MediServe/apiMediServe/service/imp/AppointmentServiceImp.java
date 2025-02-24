package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.AppointmentDTO;
import com.MediServe.apiMediServe.dto.mapper.AppointmentMapper;
import com.MediServe.apiMediServe.enums.SchedulingStatus;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.Appointment;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.AppointmentRepository;
import com.MediServe.apiMediServe.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    private final AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDTO createScheduling(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        Doctor doctor = doctorRepository.findById(appointmentDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com id: " + appointmentDTO.doctorId()));
        appointment.setDoctor(doctor);

        Patient patient = patientRepository.findById(appointmentDTO.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado com id: " + appointmentDTO.patientId()));
        appointment.setPatient(patient);

        Clinic clinic = clinicRepository.findById(appointmentDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException("Clinica não encontrada com id: " + appointmentDTO.clinicId()));
        appointment.setClinic(clinic);

        ValidadorHorarioFuncionamentoClinica(appointmentDTO);

        appointment.setSchedulingStatus(SchedulingStatus.MARCADA);

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentDTO findByIdAppoitment(Long id) {
       return appointmentMapper.toDTO(appointmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

    private void ValidadorHorarioFuncionamentoClinica(AppointmentDTO appointmentDTO){
        var sunday = appointmentDTO.appointmentDateTime().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = appointmentDTO.appointmentDateTime().getHour() < 7;
        var depoisDoEncerramentoDaClinica = appointmentDTO.appointmentDateTime().getHour() > 18;

        if (sunday || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new RuntimeException("Consulta fora do harário de funcionamento da clinica");
        }

    }

}
