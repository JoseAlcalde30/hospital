package com.example.seguridad.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seguridad.entity.Menor;

public interface MenorRepository extends JpaRepository<Menor, Long> {
    // Puedes agregar métodos personalizados si necesitas más adelante

}