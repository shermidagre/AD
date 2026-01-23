package org.example;

import org.example.model.Peliculas;
import org.example.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Secuencia {

    private final PeliculasService peliculasService;

    @Autowired
    public Secuencia(PeliculasService peliculasService) {
        this.peliculasService = peliculasService;
    }

    public void ejecuccionSecuencia() {

       List<Peliculas> equipos = peliculasService.listarPeliculas();

    }
}
