package com.example.seguridad.service;

import org.springframework.stereotype.Service;
import com.example.seguridad.entity.Menor;
import com.example.seguridad.repository.MenorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class MenorService {

    @Autowired
    private MenorRepository menorRepo;

    public List<Menor> listar() {
        return menorRepo.findAll();
    }

    public Menor  guardar(Menor menor) {
        return menorRepo.save(menor);
    }

    public void eliminar(Long id) {
        menorRepo.deleteById(id);
    }
    
    public Menor buscarPorId(Long id) {
        return menorRepo.findById(id).orElse(null);
    }


  

	}

