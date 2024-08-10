package com.MediServe.apiMediServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "O nome é obrigatório")
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "specialties")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Doctor> doctors;
}
