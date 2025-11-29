package com.example.clientes.Repository;

import com.example.clientes.Model.CategoriaTema;
import com.example.clientes.Model.Tema;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class TemaRepository {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    public void init() {
        jdbc = new JdbcTemplate(dataSource);
        popularTemas();
    }

    public List<Tema> findAll() {
        String sql = "SELECT * FROM tema";
        List<Map<String, Object>> rows = jdbc.queryForList(sql);

        List<Tema> temas = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Integer id = (Integer) row.get("id");
            String nome = (String) row.get("nome_tema");
            String thumbnail = (String) row.get("thumbnail_path");

            List<CategoriaTema> categorias = buscarCategoriasPorTema(id);
            temas.add(new Tema(id, nome, categorias, thumbnail));
        }

        return temas;
    }

    private List<CategoriaTema> buscarCategoriasPorTema(Integer temaId) {
        String sql = "SELECT c.nome FROM categoria_tema c " +
                "JOIN tema_categoria tc ON c.id = tc.categoria_id " +
                "WHERE tc.tema_id = ?";
        List<String> nomes = jdbc.queryForList(sql, String.class, temaId);

        List<CategoriaTema> categorias = new ArrayList<>();
        for (String nome : nomes) {
            try {
                categorias.add(CategoriaTema.valueOf(nome));
            } catch (IllegalArgumentException e) {
                // ignora categorias inválidas
            }
        }
        return categorias;
    }

    private void popularTemas() {
        Integer count = jdbc.queryForObject("SELECT COUNT(*) FROM tema", Integer.class);
        if (count != null && count > 0) return;

        // Inserir categorias fixas
        for (CategoriaTema categoria : CategoriaTema.values()) {
            jdbc.update("INSERT INTO categoria_tema (nome) VALUES (?) ON CONFLICT (nome) DO NOTHING", categoria.name());
        }

        // Inserir temas fixos
        inserirTema("Festa Princesas", "/img/temas/iPrincesas.jpeg", List.of(CategoriaTema.INFANTIL, CategoriaTema.FEMININO));
        inserirTema("Festa Palmeiras", "/img/temas/aPalmeiras.jpeg", List.of(CategoriaTema.ADULTO, CategoriaTema.MASCULINO));
        inserirTema("Mesversário Astronauta", "/img/temas/mAstronauta.jpeg", List.of(CategoriaTema.MESVERSARIO, CategoriaTema.MASCULINO, CategoriaTema.FEMININO));
    }

    private void inserirTema(String nome, String thumbnail, List<CategoriaTema> categorias) {
        jdbc.update("INSERT INTO tema (nome_tema, thumbnail_path) VALUES (?, ?)", nome, thumbnail);
        Integer temaId = jdbc.queryForObject("SELECT id FROM tema WHERE nome_tema = ?", Integer.class, nome);

        for (CategoriaTema categoria : categorias) {
            Integer categoriaId = jdbc.queryForObject("SELECT id FROM categoria_tema WHERE nome = ?", Integer.class, categoria.name());
            jdbc.update("INSERT INTO tema_categoria (tema_id, categoria_id) VALUES (?, ?)", temaId, categoriaId);
        }
    }
}
