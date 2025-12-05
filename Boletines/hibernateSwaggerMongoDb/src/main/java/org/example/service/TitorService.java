package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.model.Titor;
import org.example.repository.TitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitorService {

    private final TitorRepository titorRepository;

    @Autowired
    public TitorService(TitorRepository titorRepository) {
        this.titorRepository = titorRepository;
    }

    @Transactional
    public Titor crearOactualizarTitor(Titor titor) {
        return titorRepository.save(titor);
    }

    @Transactional
    public Titor actualizarTitorExistente(Long id, Titor nuevoTitor) {
        Titor titor = titorRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Titor no encontrado"));

        titor.setNome(nuevoTitor.getNome());
        titor.setApelidos(nuevoTitor.getApelidos());
        return titorRepository.save(titor);
    }

    public List<Titor> obtenerTodosOsTitores() {
        return titorRepository.findAll();
    }

    public Optional<Titor> obtenerTitorPorId(Long id) {
        return titorRepository.findById(String.valueOf(id));
    }

    public boolean eliminarTitor(Long id) {
        if (titorRepository.existsById(String.valueOf(id))) {
            titorRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }
}