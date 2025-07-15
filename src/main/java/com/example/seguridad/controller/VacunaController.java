package com.example.seguridad.controller;

import com.example.seguridad.entity.Vacuna;
import com.example.seguridad.service.VacunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacunas/admin")
public class VacunaController {

    @Autowired
    private VacunaService vacunaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("vacunas", vacunaService.listar());
        return "vacunas/admin/lista"; 
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("vacuna", new Vacuna());
        return "vacunas/admin/formulario"; 
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Vacuna vacuna) {
        vacunaService.guardar(vacuna);
        return "redirect:/vacunas/admin";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Vacuna vacuna = vacunaService.buscarPorId(id);
        if (vacuna == null) {
            return "redirect:/vacunas/admin";
        }
        model.addAttribute("vacuna", vacuna);
        return "vacunas/admin/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        vacunaService.eliminar(id);
        return "redirect:/vacunas/admin";
    }
    
}
