package com.example.clientes.Service;

import com.example.clientes.Model.Cliente;
import com.example.clientes.Repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<Cliente> criarSeNaoExistir(Cliente cliente) {
        String email = cliente.getEmail() == null ? "" : cliente.getEmail().trim();
        if (email.isEmpty()) {
            return Optional.empty();
        }
        if (repo.findByEmail(email).isPresent()) {
            return Optional.empty();
        }

        Cliente salvo = repo.save(cliente);
        return Optional.of(salvo);
    }
}
