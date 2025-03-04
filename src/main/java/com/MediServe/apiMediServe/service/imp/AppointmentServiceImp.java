package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.appointment.AppointmentDTO;
import com.MediServe.apiMediServe.dto.appointment.AppointmentMapper;
import com.MediServe.apiMediServe.enums.StatusAppointment;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

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
        clinicRepository.findById(appointmentDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException("Clinica não encontrada com o id: " + appointmentDTO.clinicId()));

        doctorRepository.findById(appointmentDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException("Médico não encontrado com o id: " + appointmentDTO.doctorId()));

        patientRepository.findById(appointmentDTO.pacientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + appointmentDTO.pacientId()));

        validateAppointmentTime(appointmentDTO);

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);

        return appointmentMapper.toDTO(appointmentRepository.save(appointment));

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
