package com.example.clientes.Service;

import com.example.clientes.Model.CategoriaTema;
import com.example.clientes.Model.Tema;
import com.example.clientes.Repository.TemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemaService {
    private final TemaRepository repo;

    public TemaService(TemaRepository repo) {
        this.repo = repo;
    }

    public List<Tema> listarTemas() {
        return repo.findAll();
    }

    public Optional<Tema> primeiroPorCategoria(CategoriaTema categoria) {
        return repo.findAll().stream()
                .filter(t -> t.getCategorias() != null && t.getCategorias().contains(categoria))
                .findFirst();
    }
}
