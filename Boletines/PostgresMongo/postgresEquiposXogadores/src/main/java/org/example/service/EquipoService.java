package org.example.service;

import org.example.model.Equipo;
import org.example.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Equipo save(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public boolean existe(Long id) {
        return equipoRepository.existsById(id);
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
    public List<Equipo> obterEquipoNome(String nome) {
        return equipoRepository.findByNome(nome);
    }

    public List<Equipo> obterEquipoCidade(String cidade) {
        return equipoRepository.findByCidade(cidade);
    }

    public Optional<Equipo> findById(Long id) {
        return equipoRepository.findById(id);
    }

    public List<Equipo> obterTodosEquipos() {
        return equipoRepository.findAll();
    }

}
