package com.MediServe.apiMediServe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    @NotEmpty(message = "O CEP é obrigatório")
    private String cep;
    @NotEmpty(message = "O nome da rua é obrigatório")
    private String logradouro;
    @NotEmpty(message = "O Número é obrigatório")
    private String locationNumber;
    @NotEmpty(message = "O bairro é obrigatório")
    private String neighborhood;
    @NotEmpty(message = "A cidade é obrigatório")
    private String city;
    @NotEmpty(message = "O estado é obrigatório")
    private String state;
}
