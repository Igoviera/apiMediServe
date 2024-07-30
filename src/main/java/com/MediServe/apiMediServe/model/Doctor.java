package com.MediServe.apiMediServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
    private String name;
    private String crm;
    private String cpf;
    private String phone;
    private BigDecimal queryValue;
    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialty",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialties;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OpeningHours> openingHours;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Clinic clinic;
}
