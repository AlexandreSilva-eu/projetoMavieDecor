package com.example.clientes.Model;

import java.util.List;

public class Tema {
    private Integer id;
    private String nomeTema;
    private List<CategoriaTema> categorias;
    private String thumbnailPath;

    public Tema() {}

    public Tema(Integer id, String nomeTema, List<CategoriaTema> categorias, String thumbnailPath) {
        this.id = id;
        this.nomeTema = nomeTema;
        this.categorias = categorias;
        this.thumbnailPath = thumbnailPath;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeTema() { return nomeTema; }
    public void setNomeTema(String nomeTema) { this.nomeTema = nomeTema; }

    public List<CategoriaTema> getCategorias() { return categorias; }
    public void setCategorias(List<CategoriaTema> categorias) { this.categorias = categorias; }

    public String getThumbnailPath() { return thumbnailPath; }
    public void setThumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; }
}
