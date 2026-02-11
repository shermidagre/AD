package org.example.repository;

import org.example.model.Personajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajesRepository extends JpaRepository<Personajes,Long> {
    List<Personajes> findByNombre(String nombre);
    List<Personajes> findByNacionalidad(String nacionalidad);
}
