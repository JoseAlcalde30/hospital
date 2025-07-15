package com.example.seguridad.service;

import com.example.seguridad.entity.Vacunacion;
import com.example.seguridad.repository.VacunacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunacionService {

    @Autowired
    private VacunacionRepository vacunacionRepository;

    public List<Vacunacion> listarTodas() {
        return vacunacionRepository.findAll();
    }

    public Vacunacion guardar(Vacunacion vacunacion) {
        return vacunacionRepository.save(vacunacion);
    }

    public void eliminar(Long id) {
        vacunacionRepository.deleteById(id);
    }

    public Vacunacion buscarPorId(Long id) {
        return vacunacionRepository.findById(id).orElse(null);
    }

    public List<Vacunacion> buscarPorTipoVacuna(String tipoVacuna) {
        return vacunacionRepository.findByTipoVacuna(tipoVacuna);
    }

    public List<Vacunacion> buscarPorMenor(Long menorId) {
        return vacunacionRepository.findByMenorId(menorId);
    }
}
