package com.MediServe.apiMediServe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 12345-678")
    private String cep;
    @NotBlank(message = "O nome da rua é obrigatório")
    private String logradouro;
    @NotBlank(message = "O Número é obrigatório")
    private String locationNumber;
    @NotBlank(message = "O bairro é obrigatório")
    private String neighborhood;
    @NotBlank(message = "A cidade é obrigatório")
    private String city;
    @NotBlank(message = "O estado é obrigatório")
    private String uf;
}
