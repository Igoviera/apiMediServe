package com.MediServe.apiMediServe.service.imp;

import com.MediServe.apiMediServe.dto.address.AddressMapper;
import com.MediServe.apiMediServe.dto.clinic.ClinicDTO;
import com.MediServe.apiMediServe.exception.EmailAlreadyExists;
import com.MediServe.apiMediServe.exception.RecordNotFoundException;
import com.MediServe.apiMediServe.dto.clinic.ClinicMapper;
import com.MediServe.apiMediServe.model.Clinic;
import com.MediServe.apiMediServe.repository.ClinicRepository;
import com.MediServe.apiMediServe.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicServiceImp implements ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper;
    private final AddressMapper addressMapper;

    @Override
    public ClinicDTO createClinic(ClinicDTO clinicDTO) {
        if (clinicRepository.existsByEmail(clinicDTO.email())){
           throw new EmailAlreadyExists();
        }
        return clinicMapper.toDTO(clinicRepository.save(clinicMapper.toEntity(clinicDTO)));
    }

    @Override
    public List<ClinicDTO> findAllClinic() {
        return clinicRepository.findAll().stream()
                .map(clinic -> clinicMapper.toDTO(clinic))
                .collect(Collectors.toList());
    }

    @Override
    public ClinicDTO findByIdClinic(Long id) {
        return clinicMapper.toDTO(clinicRepository.findById(id).
                orElseThrow(() -> new RecordNotFoundException(id)));

    }

    @Override
    public ClinicDTO updateClinic(Long clinicId, ClinicDTO clinicDTO) {
       Clinic clinicFound = clinicRepository.findById(clinicId)
               .orElseThrow(() -> new RecordNotFoundException(clinicId));

        clinicFound.setName(clinicDTO.name());
        clinicFound.setEmail(clinicDTO.email());
        clinicFound.setPhone(clinicDTO.phone());
        clinicFound.setImgURL(clinicDTO.imgURL());
        clinicFound.setAddress(addressMapper.toEntity(clinicDTO.address()));

        return clinicMapper.toDTO(clinicRepository.save(clinicFound));
    }
}
