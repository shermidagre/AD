package org.example.service;

import org.example.model.Actores;
import org.example.model.Peliculas;
import org.example.repository.ActoresRepository;
import org.example.repository.PeliculasRepository;
import org.springframework.stereotype.Service;

@Service
public class ActoresService {

    private final ActoresRepository actoresRepository;
    private final PeliculasRepository peliculasRepository;

    public ActoresService(ActoresRepository actoresRepository, PeliculasRepository peliculasRepository) {
        this.actoresRepository = actoresRepository;
        this.peliculasRepository = peliculasRepository;
    }

    public void crearActor(Actores actores) {
        actoresRepository.save(actores);
    }

}
