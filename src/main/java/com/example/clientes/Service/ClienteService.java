package com.example.clientes.Service;

import com.example.clientes.Model.Cliente;
import com.example.clientes.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public void atualizar(Integer id, Cliente cliente) {
        cliente.setId(id);
        repository.updateCliente(id, cliente);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Cliente> criarSeNaoExistir(Cliente cliente) {
        Optional<Cliente> existente = repository.findByEmail(cliente.getEmail());
        if (existente.isPresent()) {
            return Optional.empty();
        }
        Cliente salvo = repository.save(cliente);
        return Optional.ofNullable(salvo);
    }
}
