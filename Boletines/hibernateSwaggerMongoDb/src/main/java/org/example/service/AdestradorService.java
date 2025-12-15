package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Adestrador;
import org.example.repository.AdestradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdestradorService {

    private final AdestradorRepository adestradorRepository;

    @Autowired
    public AdestradorService(AdestradorRepository adestradorRepository) {
        this.adestradorRepository = adestradorRepository;
    }

    @Transactional
    public Adestrador crearAdestrador(Adestrador adestrador) {
        return adestradorRepository.save(adestrador);
    }

    @Transactional
    public Adestrador actualizarAdestrador(Long id, Adestrador nuevoAdestrador) {
        Adestrador adestrador = adestradorRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Titor no encontrado"));

        adestrador.setNome(nuevoAdestrador.getNome());
        adestrador.setCidade(nuevoAdestrador.getCidade());
        return adestradorRepository.save(adestrador);
    }

    public List<Adestrador> obtenerAdestradores() {
        return adestradorRepository.findAll();
    }

    public Optional<Adestrador> obtenerAdestradorId(Long id) {
        return adestradorRepository.findById(String.valueOf(id));
    }

    public boolean eliminarAdestradorId(Long id) {
        if (adestradorRepository.existsById(String.valueOf(id))) {
            adestradorRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}