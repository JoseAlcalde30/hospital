package com.example.seguridad.repository;

import com.example.seguridad.entity.Vacunacion;
import com.example.seguridad.entity.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacunacionRepository extends JpaRepository<Vacunacion, Long> {

    @Query("SELECT v FROM Vacunacion v WHERE v.vacuna.tipo = :tipoVacuna")
    List<Vacunacion> findByTipoVacuna(String tipoVacuna);

    List<Vacunacion> findByMenorId(Long menorId);
}
