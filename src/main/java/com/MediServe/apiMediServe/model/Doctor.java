package com.MediServe.apiMediServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "O nome é obrigatório")
    private String name;
    private String imgUrl;
    @NotEmpty(message = "O CRM é obrigatório")
    private String crm;
    @NotEmpty(message = "O CPF é obrigatório")
    private String cpf;
    @NotEmpty(message = "O telefone é obrigatório")
    private String phone;
    private String description;
//    @NotEmpty(message = "O valor é obrigatório")
//    @Positive(message = "O valor tem que ser positivo")
    private BigDecimal queryValue;
    @Embedded
    @Valid
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialty",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DoctorDiary> doctorDiaries = new ArrayList<>();

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Clinic clinic;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Scheduling> schedulings;
}
