package com.example.clientes.Repository;

import com.example.clientes.Model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {
    private final Map<Long, Cliente> store = new LinkedHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public List<Cliente> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Cliente> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Optional<Cliente> findByEmail(String email) {
        return store.values().stream()
                .filter(c -> c.getEmail() != null && c.getEmail().equalsIgnoreCase(email.trim()))
                .findFirst();
    }

    public Cliente save(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(idGenerator.incrementAndGet());
        }
        store.put(cliente.getId(), cliente);
        return cliente;
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public void clear() {
        store.clear();
    }
}
