package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentDetailDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.enums.StatusAppointment;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.*;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.AppointmentRepository;
import com.MediServe.apiMediServe.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    @Autowired
    private final DoctorRepository doctorRepository;

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired AppointmentRepository appointmentRepository;

    @Autowired ClinicRepository clinicRepository;

    @Autowired AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {

        Clinic clinic = clinicRepository.findById(appointmentDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException("Clinica não encontrada com o id: " + appointmentDTO.clinicId()));

        Doctor doctor = doctorRepository.findById(appointmentDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com o id: " + appointmentDTO.doctorId()));

        Patient patient = patientRepository.findById(appointmentDTO.patientId())
                .orElseThrow(() -> new RecordNotFoundException("Paciente não encontrado com id: " + appointmentDTO.patientId()));

        validateAppointmentTime(appointmentDTO);

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        appointment.setClinicId(clinic);
        appointment.setDoctorId(doctor);
        appointment.setPatientId(patient);

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));

    }

    @Override
    public List<AppointmentDetailDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(appointment -> appointmentMapper.toDetailDTO(appointment))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO findById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Consulta não encontrada"));

        return appointmentMapper.toDTO(appointment);
    }

    public void validateAppointmentTime (AppointmentDTO appointmentDTO) {
        var dateAppointment = appointmentDTO.data();

        boolean isSunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = dateAppointment.getHour() < 7;
        boolean afterClosing  = dateAppointment.getHour() > 18;

        if (isSunday || beforeOpening || afterClosing){
            throw new IllegalArgumentException("A consulta deve ser agendada entre 07:00 e 18:00, de segunda a sábado.");
        }
    }

   public void validateExistsAppointment(AppointmentDTO appointmentDTO){
        Doctor doctor = doctorRepository.findById(appointmentDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com o id: " + appointmentDTO.doctorId()));

       var existsAppointment = appointmentRepository.existsByDoctorIdAndAppointmentDateTime(doctor, appointmentDTO.data());

       if (existsAppointment){
           throw new IllegalArgumentException("O médico já tem uma consulta agendada para esse horário.");
       }
   }
}
