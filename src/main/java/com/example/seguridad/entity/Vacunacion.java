package com.example.seguridad.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacunaciones")
public class Vacunacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "menor_id")
    private Menor menor;

    @ManyToOne
    @JoinColumn(name = "vacuna_id")
    private Vacuna vacuna;
}
