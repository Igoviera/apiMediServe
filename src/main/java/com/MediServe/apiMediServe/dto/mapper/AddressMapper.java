package com.MediServe.apiMediServe.dto.mapper;

import com.MediServe.apiMediServe.dto.AddressDTO;
import com.MediServe.apiMediServe.model.Address;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapper {
    public AddressDTO toDTO(Address address){
        if (address == null){
            return null;
        }

        return new AddressDTO(
                address.getCep(),
                address.getLogradouro(),
                address.getLocationNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getUf()
        );
    }

    public Address toEntity(AddressDTO addressDTO){
        if(addressDTO == null){
            return null;
        }

        Address address = new Address();
        address.setCep(addressDTO.cep());
        address.setLogradouro(addressDTO.logradouro());
        address.setLocationNumber(addressDTO.locationNumber());
        address.setNeighborhood(addressDTO.neighborhood());
        address.setCity(addressDTO.city());
        address.setUf(addressDTO.uf());

        return address;
    }
}
