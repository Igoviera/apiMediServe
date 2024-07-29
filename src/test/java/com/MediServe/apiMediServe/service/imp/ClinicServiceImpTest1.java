package com.MediServe.apiMediServe.service.imp;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.mapper.clinic.ClinicMapper;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.service.ClinicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

@SpringBootTest
class ClinicServiceImpTest1 {

    @Mock
    private ClinicRepository clinicRepository;

    @Mock
    private ClinicMapper clinicMapper;

    @InjectMocks
    private ClinicService clinicService;

    private ClinicDTO clinicDTO;
    private Clinic clinic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clinicDTO = new ClinicDTO(
                null,
                "Clinic Name",
                "clinic@example.com",
                "123456789",
                "9:00",
                "18:00",
                "http://example.com/image.jpg"
        );
        clinic = new Clinic(
                null,
                "Clinic Name",
                "clinic@example.com",
                "123456789",
                LocalTime.of(9, 0),
                LocalTime.of(18, 0),
                "http://example.com/image.jpg"
        );
    }

    @Test
    void testCreateClinic() {
        when(clinicMapper.toEntity(clinicDTO)).thenReturn(clinic);
        when(clinicRepository.save(clinic)).thenReturn(clinic);
        when(clinicMapper.toDTO(clinic)).thenReturn(clinicDTO);

        ClinicDTO result = clinicService.createClinic(clinicDTO);

        assertNotNull(result);
        assertEquals(clinicDTO.name(), result.name());
        assertEquals(clinicDTO.email(), result.email());
        assertEquals(clinicDTO.phone(), result.phone());
        assertEquals(clinicDTO.openingTime(), result.openingTime());
        assertEquals(clinicDTO.closingTime(), result.closingTime());
        assertEquals(clinicDTO.imgURL(), result.imgURL());

        verify(clinicMapper).toEntity(clinicDTO);
        verify(clinicRepository).save(clinic);
        verify(clinicMapper).toDTO(clinic);
    }
}
