package com.example.seguridad.repository;

import com.example.seguridad.entity.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
	
	
}
