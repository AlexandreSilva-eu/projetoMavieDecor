package com.example.clientes.Model;
import java.util.ArrayList;
import java.util.List;

public class Tema {
    private String nomeTema;
    private ArrayList<CategoriaTema> categorias = new ArrayList<CategoriaTema>();
    private String thumbnailPath; // caminho da imagem

    public Tema(String nomeTema, ArrayList<CategoriaTema> categorias, String thumbnailPath) {
        this.nomeTema = nomeTema;
        this.categorias = categorias;
        this.thumbnailPath = thumbnailPath;
    }

    public String getNomeTema() {
        return nomeTema;
    }

    public void setNomeTema(String nomeTema) {
        this.nomeTema = nomeTema;
    }

    public ArrayList<CategoriaTema> getCategorias() {
        return categorias;
    }

    public void setCategoria(ArrayList<CategoriaTema> categorias) {
        this.categorias = categorias;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
