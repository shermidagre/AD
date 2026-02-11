package org.example.service;

import org.example.model.Partes;
import org.example.repository.PartesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartesService {

    private final PartesRepository partesRepository;

    @Autowired
    public PartesService(PartesRepository partesRepository) {
        this.partesRepository = partesRepository;
    }

    public Partes save(Partes parte) {
        return partesRepository.save(parte);
    }

    public boolean exists(Long id) {
        return partesRepository.existsById(id);
    }

    public void delete(Long id) {
        partesRepository.deleteById(id);
    }
    public List<Partes> obtenerPartesPorNombre(String nombre) {
        return partesRepository.findByNombre(nombre);
    }

    public Optional<Partes> findById(Long id) {
        return partesRepository.findById(id);
    }

    public List<Partes> obtenerTodasLasPartes() {
        return partesRepository.findAll();
    }
}
