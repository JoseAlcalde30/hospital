package com.example.seguridad.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacunas")
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la vacuna es obligatorio")
    private String nombre;

    @NotBlank(message = "El laboratorio es obligatorio")
    private String laboratorio;

    @NotBlank(message = "El tipo de vacuna es obligatorio")
    private String tipo;  

    @OneToMany(mappedBy = "vacuna")
    private List<Vacunacion> vacunaciones;
}
