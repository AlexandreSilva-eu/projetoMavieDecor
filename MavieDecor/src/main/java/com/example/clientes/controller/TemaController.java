package com.example.clientes.controller;

import com.example.clientes.Model.CategoriaTema;
import com.example.clientes.Service.TemaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemaController {
    private final TemaService service;

    public TemaController(TemaService service) {
        this.service = service;
    }

    @GetMapping("/temas")
    public String listarTemas(@RequestParam(value = "categoria", required = false) String categoria,
                              Model model) {

        CategoriaTema filtro = null;
        if (categoria != null && !categoria.isBlank()) {
            try {
                filtro = CategoriaTema.valueOf(categoria);
            } catch (IllegalArgumentException e) {
                filtro = null;
            }
        }

        model.addAttribute("temas", service.listarPorCategoria(filtro));
        model.addAttribute("categorias", CategoriaTema.values());
        model.addAttribute("categoriaSelecionada", filtro != null ? filtro.name() : "");
        return "temas";
    }

}
