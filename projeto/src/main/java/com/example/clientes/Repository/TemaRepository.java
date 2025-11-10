package com.example.clientes.Repository;

import com.example.clientes.Model.CategoriaTema;
import com.example.clientes.Model.Tema;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TemaRepository {
    private final List<Tema> temas = new ArrayList<>();

    @PostConstruct
    public void init() {
        Tema iPrincesas = new Tema("Festa Princesas", new ArrayList<CategoriaTema>(), "/img/temas/iPrincesas.jpeg");
        iPrincesas.getCategorias().add(CategoriaTema.INFANTIL); iPrincesas.getCategorias().add(CategoriaTema.FEMININO);

        Tema aPalmeiras = new Tema("Festa Palmeiras", new ArrayList<CategoriaTema>(), "/img/temas/aPalmeiras.jpeg");
        aPalmeiras.getCategorias().add(CategoriaTema.ADULTO); aPalmeiras.getCategorias().add(CategoriaTema.MASCULINO);

        Tema mAstronauta = new Tema("Mesversário Astronauta", new ArrayList<CategoriaTema>(), "/img/temas/mAstronauta.jpeg");
        mAstronauta.getCategorias().add(CategoriaTema.MESVERSARIO); mAstronauta.getCategorias().add(CategoriaTema.MASCULINO); mAstronauta.getCategorias().add(CategoriaTema.FEMININO);

        temas.add(iPrincesas); temas.add(aPalmeiras); temas.add(mAstronauta);
    }

    public List<Tema> findAll() {
        return temas;
    }
}
