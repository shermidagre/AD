package org.example.service;

import org.example.model.Xogador;
import org.example.repository.XogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XogadorService {

    private final XogadorRepository xogadorRepository;

    public XogadorService(XogadorRepository xogadorRepository) {
        this.xogadorRepository = xogadorRepository;
    }

    public List<Xogador> findAll() {
        return xogadorRepository.findAll();
    }

    public Optional<Xogador> findById(Long id) {
        return xogadorRepository.findById(id);
    }

    public Xogador save(Xogador xogador) {
        return xogadorRepository.save(xogador);
    }

    public boolean existsById(Long id) {
        return xogadorRepository.existsById(id);
    }

    public void deleteById(Long id) {
        xogadorRepository.deleteById(id);
    }
}
