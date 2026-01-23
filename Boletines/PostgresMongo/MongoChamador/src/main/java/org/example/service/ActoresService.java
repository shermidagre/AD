package org.example.service;

import org.example.model.Actores;
import org.example.repository.ActoresRepository;
import org.springframework.stereotype.Service;

@Service
public class ActoresService {

    private final ActoresRepository actoresRepository;

    public ActoresService(ActoresRepository actoresRepository) {
        this.actoresRepository = actoresRepository;
    }

    public void crearActor(Actores actores) {
        actoresRepository.save(actores);
    }

}
