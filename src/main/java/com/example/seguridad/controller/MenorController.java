package com.example.seguridad.controller;

import com.example.seguridad.entity.Menor;
import com.example.seguridad.service.MenorService;

import jakarta.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menores")
public class MenorController {

    private final MenorService menorService;

    public MenorController(MenorService menorService) {
        this.menorService = menorService;
    }

    @GetMapping("/usuario")
    public String vistaUsuario(Model model) {
        model.addAttribute("menores", menorService.listar());
        return "menores/usuario/lista";
    }

    @GetMapping("/admin")
    public String vistaAdmin(Model model) {
        model.addAttribute("menores", menorService.listar());
        return "menores/admin/lista";
    }

    @GetMapping("/admin/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("menor", new Menor());
        return "menores/admin/formulario";
    }

    @PostMapping("/admin/guardar")
    public String guardarMenor(@Valid @ModelAttribute Menor menor,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "menores/admin/formulario";
        }

        try {
            menorService.guardar(menor);
        } catch (DataIntegrityViolationException ex) {
            model.addAttribute("errorDni", "El DNI ingresado ya está registrado.");
            return "menores/admin/formulario";
        }

        return "redirect:/menores/admin";
    }


    @GetMapping("/admin/editar/{id}")
    public String editarMenor(@PathVariable Long id, Model model) {
        Menor menor = menorService.buscarPorId(id);
        if (menor == null) {
            return "redirect:/menores/admin";
        }
        System.out.println("Fecha nacimiento al editar: " + menor.getFechaNacimiento());

        model.addAttribute("menor", menor);
        return "menores/admin/formulario";
    }




}
