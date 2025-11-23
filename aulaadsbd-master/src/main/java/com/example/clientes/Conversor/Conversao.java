package com.example.clientes.Conversor;

import com.example.clientes.Model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversao {

    public static ArrayList<Cliente> converterClientes(List<Map<String,Object>> mapa){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Map<String,Object> registro : mapa){
            int id = (Integer) registro.get("id");
            String nome = (String) registro.get("nome");
            String email = (String) registro.get("email");
            String telefone = (String) registro.get("telefone");
            String senha = (String) registro.get("senha");
            clientes.add(new Cliente(id,nome,email,telefone,senha));
        }
        return clientes;
    }

}
