package com.example.seguridad.controller;

import com.example.seguridad.entity.Menor;
import com.example.seguridad.entity.Vacunacion;
import com.example.seguridad.entity.Vacuna;
import com.example.seguridad.service.MenorService;
import com.example.seguridad.service.VacunacionService;
import com.example.seguridad.service.VacunaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vacunaciones")
public class VacunacionController {

    @Autowired
    private VacunacionService vacunacionService;

    @Autowired
    private MenorService menorService;

    @Autowired
    private VacunaService vacunaService;

    @GetMapping("/admin")
    public String listarVacunaciones(Model model) {
        model.addAttribute("vacunaciones", vacunacionService.listarTodas());
        return "vacunaciones/admin/lista";
    }
    
    @GetMapping("/usuario")
    public String vistaUsuario(Model model) {
        model.addAttribute("vacunaciones", vacunacionService.listarTodas());
        return "vacunaciones/usuario/lista";
    }


    @GetMapping("/admin/nueva")
    public String mostrarFormulario(Model model) {
        model.addAttribute("vacunacion", new Vacunacion());
        model.addAttribute("menores", menorService.listar());
        model.addAttribute("vacunas", vacunaService.listar());
        return "vacunaciones/admin/formulario";
    }

    @PostMapping("/admin/guardar")
    public String guardar(
        @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
        @RequestParam("observaciones") String observaciones,
        @RequestParam("menorId") Long menorId,
        @RequestParam("vacunaId") Long vacunaId
    ) {
        Menor menor = menorService.buscarPorId(menorId);
        Vacuna vacuna = vacunaService.buscarPorId(vacunaId);

        if (menor == null || vacuna == null) {
            return "redirect:/vacunaciones/admin/nueva?error=notfound";
        }

        Vacunacion vacunacion = new Vacunacion();
        vacunacion.setFecha(fecha);
        vacunacion.setObservaciones(observaciones);
        vacunacion.setMenor(menor);
        vacunacion.setVacuna(vacuna);

        vacunacionService.guardar(vacunacion);
        return "redirect:/vacunaciones/admin";
    }

    @GetMapping("/admin/editar/{id}")
    public String editarVacunacion(@PathVariable Long id, Model model) {
        Vacunacion vacunacion = vacunacionService.buscarPorId(id);
        model.addAttribute("vacunacion", vacunacion);
        model.addAttribute("menores", menorService.listar());
        model.addAttribute("vacunas", vacunaService.listar());
        return "vacunaciones/admin/formulario";
    }


    @GetMapping("/admin/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        vacunacionService.eliminar(id);
        return "redirect:/vacunaciones/admin";
    }

    @GetMapping("/admin/consulta")
    public String mostrarConsultaForm(Model model) {
        model.addAttribute("tipos", List.of("VIRAL", "BACTERIANA", "TOXOIDE", "RECOMBINANTE", "ARN"));
        return "vacunaciones/admin/consulta";
    }

    @PostMapping("/admin/consulta")
    public String mostrarResultadosConsulta(@RequestParam("tipo") String tipo, Model model) {
        List<Vacunacion> vacunaciones = vacunacionService.buscarPorTipoVacuna(tipo);
        model.addAttribute("vacunaciones", vacunaciones);
        model.addAttribute("tipoSeleccionado", tipo);
        return "vacunaciones/admin/consulta_resultado";
    }

    @GetMapping("/usuario/menor/{id}/vacunaciones")
    public String verHistorialVacunas(@PathVariable Long id, Model model) {
        Menor menor = menorService.buscarPorId(id);
        if (menor == null) {
            return "redirect:/menores";
        }
        List<Vacunacion> vacunaciones = vacunacionService.buscarPorMenor(id);
        model.addAttribute("menor", menor);
        model.addAttribute("vacunaciones", vacunaciones);
        return "vacunaciones/usuario/historial"; // Vista corregida
    }
}
