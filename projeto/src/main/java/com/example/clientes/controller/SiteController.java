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
        Optional<Tema> infantil = temaService.primeiroPorCategoria(CategoriaTema.INFANTIL);
        Optional<Tema> adulto = temaService.primeiroPorCategoria(CategoriaTema.ADULTO);
        Optional<Tema> mesversario = temaService.primeiroPorCategoria(CategoriaTema.MESVERSARIO);

        model.addAttribute("temaInfantil", infantil.orElse(null));
        model.addAttribute("temaAdulto", adulto.orElse(null));
        model.addAttribute("temaMesversario", mesversario.orElse(null));
        return "homepage";
    }
}
