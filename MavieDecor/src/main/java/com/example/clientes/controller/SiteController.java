package com.example.clientes.controller;

import com.example.clientes.Model.CategoriaTema;
import com.example.clientes.Model.Tema;
import com.example.clientes.Service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class SiteController {

    private final TemaService temaService;

    public SiteController(TemaService temaService) {
        this.temaService = temaService;
    }

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/homepage")
    public String homepage(Model model) {
        model.addAttribute("temaInfantil", temaService.primeiroPorCategoria(CategoriaTema.INFANTIL).orElse(null));
        model.addAttribute("temaAdulto", temaService.primeiroPorCategoria(CategoriaTema.ADULTO).orElse(null));
        model.addAttribute("temaMesversario", temaService.primeiroPorCategoria(CategoriaTema.MESVERSARIO).orElse(null));
        return "homepage";
    }
}
