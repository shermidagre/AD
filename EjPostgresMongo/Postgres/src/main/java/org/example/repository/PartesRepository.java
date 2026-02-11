package org.example.repository;

import org.example.model.Partes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartesRepository extends JpaRepository<Partes,Long> {
    List<Partes> findByNombre(String nombre);
    List<Partes> findByGenero(String genero);
}
