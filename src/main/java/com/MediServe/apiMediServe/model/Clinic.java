package com.MediServe.apiMediServe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "O nome é obrigatório")
    private String name;
    @NotEmpty(message = "O e-mail é obrigatório")
    @Column(unique = true)
    private String email;
    private List<String> phones;
    private String imgURL;
    //private Address address;
}
