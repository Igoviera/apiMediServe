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
    @NotEmpty(message = "Campo cep é obrigatório")
    @Column(nullable = false)
    private String cep;

    @NotEmpty(message = "Campo logradouro é obrigatório")
    private String logradouro;

    @NotEmpty(message = "Campo número é obrigatório")
    private String locationNumber;
}
