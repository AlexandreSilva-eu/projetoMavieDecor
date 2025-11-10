package com.example.clientes.controller;

import com.example.clientes.Service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemaController {
    private final TemaService service;

    public TemaController(TemaService service) {
        this.service = service;
    }

    @GetMapping("/temas")
    public String listarTemas(Model model) {
        model.addAttribute("temas", service.listarTemas());
        return "temas";
    }
}
