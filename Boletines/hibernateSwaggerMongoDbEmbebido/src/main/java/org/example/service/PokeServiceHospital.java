package org.example.service;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.example.model.Adestrador;
import org.example.repository.PokeRepositoryHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokeServiceHospital {

    @Autowired
    private final PokeRepositoryHospital pokeRepositoryHospital;

    public PokeServiceHospital(PokeRepositoryHospital pokeRepositoryHospital){
      this.pokeRepositoryHospital = pokeRepositoryHospital;
    }

    @Transactional
    public void gardarAdestrador(Adestrador adestrador) {
        pokeRepositoryHospital.save(adestrador);
    }

    public List<Adestrador> listarTodos() {
        return pokeRepositoryHospital.findAll();
    }

    @Transactional
    public void borrarTodos(){
        pokeRepositoryHospital.deleteAll();
    }

    @Transactional
    public void borrarAdestradorPorNombre(@Parameter String nome){
        pokeRepositoryHospital.deleteByNome(nome);
    }

    @Transactional
    public Adestrador encontrarAdestradorPorNome(@Parameter String nome, Adestrador adestradorDetails) {
        return pokeRepositoryHospital.findByNome(nome);
    }

}
