package com.example.seguridad.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menores")
public class Menor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(unique = true, nullable = false, length = 8)
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos")
    private String dni;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior a hoy")
    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;


    @NotBlank(message = "Debe seleccionar el sexo")
    @Pattern(regexp = "^(MASCULINO|FEMENINO)$", message = "El sexo debe ser MASCULINO o FEMENINO")
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "menor", cascade = CascadeType.ALL)
    private List<Vacunacion> vacunaciones;

    @Transient
    public int getEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
    @AssertTrue(message = "El menor debe tener menos de 18 años")
    @Transient
    public boolean isEdadValida() {
        return fechaNacimiento != null && Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18;
    }

}
