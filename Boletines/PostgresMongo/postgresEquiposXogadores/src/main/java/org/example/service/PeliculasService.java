package org.example.service;

import org.example.model.Peliculas;
import org.example.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculasService {

    private final PeliculasRepository peliculasRepository;

    @Autowired
    public PeliculasService(PeliculasRepository peliculasRepository) {
        this.peliculasRepository = peliculasRepository;
    }

    public Peliculas save(Peliculas peliculas) {
        return peliculasRepository.save(peliculas);
    }

    public boolean existe(Long id) {
        return peliculasRepository.existsById(id);
    }

    public void delete(Long id) {
        peliculasRepository.deleteById(id);
    }

    public Optional<Peliculas> findById(Long id) {
        return peliculasRepository.findById(id);
    }

    public Optional<Peliculas> findByTitulo(String titulo) {
        return peliculasRepository.findByTitulo(titulo);
    }

    public List<Peliculas> obterTodosEquipos() {
        return peliculasRepository.findAll();
    }

}
