package com.example.seguridad.service;

import com.example.seguridad.entity.Vacuna;
import com.example.seguridad.entity.Vacunacion;
import com.example.seguridad.repository.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaService {

    @Autowired
    private VacunaRepository vacunaRepository;

    public List<Vacuna> listar() {
        return vacunaRepository.findAll();
    }

    public Vacuna guardar(Vacuna vacuna) {
        return vacunaRepository.save(vacuna);
    }

    public void eliminar(Long id) {
        vacunaRepository.deleteById(id);
    }

    public Vacuna buscarPorId(Long id) {
        return vacunaRepository.findById(id).orElse(null);
    }
    
  
}
