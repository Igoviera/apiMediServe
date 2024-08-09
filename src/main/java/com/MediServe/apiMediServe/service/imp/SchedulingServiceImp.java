package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.SchedulingDTO;
import com.MediServe.apiMediServe.dto.mapper.SchedulingMapper;
import com.MediServe.apiMediServe.enums.SchedulingStatus;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.model.Doctor;
import com.MediServe.apiMediServe.model.Patient;
import com.MediServe.apiMediServe.model.Scheduling;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.repository.DoctorRepository;
import com.MediServe.apiMediServe.repository.PatientRepository;
import com.MediServe.apiMediServe.repository.SchedulingRepository;
import com.MediServe.apiMediServe.service.SchedulingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class SchedulingServiceImp implements SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ClinicRepository clinicRepository;

    private final SchedulingMapper schedulingMapper;

    @Override
    public SchedulingDTO createScheduling(SchedulingDTO schedulingDTO) {
        Scheduling scheduling = schedulingMapper.toEntity(schedulingDTO);

        Doctor doctor = doctorRepository.findById(schedulingDTO.doctorId())
                .orElseThrow(() -> new RecordNotFoundException(schedulingDTO.doctorId()));
        scheduling.setDoctor(doctor);

        Patient patient = patientRepository.findById(schedulingDTO.patientId())
                .orElseThrow(() -> new RecordNotFoundException(schedulingDTO.patientId()));
        scheduling.setPatient(patient);

        Clinic clinic = clinicRepository.findById(schedulingDTO.clinicId())
                .orElseThrow(() -> new RecordNotFoundException(schedulingDTO.clinicId()));
        scheduling.setClinic(clinic);

        scheduling.setSchedulingStatus(SchedulingStatus.CONFIRMADO);

        return schedulingMapper.toDTO(schedulingRepository.save(scheduling));
    }
}
