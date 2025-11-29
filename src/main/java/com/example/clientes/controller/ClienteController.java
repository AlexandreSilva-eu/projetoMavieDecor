package com.example.clientes.controller;

import com.example.clientes.Model.Cliente;
import com.example.clientes.Service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "novo";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("cliente") Cliente cliente, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "novo";
        }

        boolean exists = service.buscarPorEmail(cliente.getEmail()).isPresent();
        if (exists) {
            model.addAttribute("mensagem", "Já existe um cliente cadastrado com este email.");
            model.addAttribute("tipo", "erro");
            return "result";
        }

        service.criarSeNaoExistir(cliente)
                .ifPresentOrElse(
                        c -> {
                            model.addAttribute("mensagem", "Cliente cadastrado com sucesso.");
                            model.addAttribute("tipo", "sucesso");
                        },
                        () -> {
                            model.addAttribute("mensagem", "Erro ao cadastrar cliente.");
                            model.addAttribute("tipo", "erro");
                        }
                );

        return "result";
    }

    // redireciona raiz /clientes para novo
    @GetMapping
    public String root() {
        return "redirect:/clientes/novo";
    }
}
