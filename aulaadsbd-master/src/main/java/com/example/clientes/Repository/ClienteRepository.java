package com.example.clientes.Repository;

import com.example.clientes.Conversor.Conversao;
import com.example.clientes.Model.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ClienteRepository {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public Cliente save(Cliente cliente) {
        String sql = "INSERT INTO Cliente(nome,email,telefone,senha) VALUES(?,?,?,?)";
        Object[] obj = new Object[4];
        obj[0] = cliente.getNome();
        obj[1] = cliente.getEmail();
        obj[2] = cliente.getTelefone();
        obj[3] = cliente.getSenha();
        jdbc.update(sql, obj);
        return cliente;
    }

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM Cliente";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        return Conversao.converterClientes(mapa);
    }

    public Optional<Cliente> findById(Integer id) {
        String sql = "SELECT * FROM Cliente where id=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, id);
        int idCliente = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String email = (String) mapa.get("email");
        String telefone = (String) mapa.get("telefone");
        String senha = (String) mapa.get("senha");
        Cliente cli = new Cliente(idCliente,nome,email,telefone,senha);
        return Optional.of(cli);
    }

    public Optional<Cliente> findByEmail(String email) {
        String sql = "SELECT * FROM Cliente where email=?";
        Map<String,Object> mapa = jdbc.queryForMap(sql, email);
        int id = (Integer) mapa.get("id");
        String nome = (String) mapa.get("nome");
        String emailCliene = (String) mapa.get("email");
        String telefone = (String) mapa.get("telefone");
        String senha = (String) mapa.get("senha");
        Cliente cli = new Cliente(id,nome,emailCliene,telefone,senha);
        return Optional.of(cli);
    }

    public void updateCliente(int id, Cliente cliente){
        String sql = "UPDATE Cliente SET nome = ?, email = ?, telefone = ?, senha = ? WHERE id = ?";
        Object[] obj = new Object[5];
        obj[0] = cliente.getNome();
        obj[1] = cliente.getEmail();
        obj[2] = cliente.getTelefone();
        obj[3] = cliente.getSenha();
        obj[4] = cliente.getId();
        jdbc.update(sql, obj);
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM Cliente WHERE id=?";
        jdbc.update(sql, id);
    }

    public void deletarTudo() {
        String sql = "DROP TABLE cliente";
    }
}
