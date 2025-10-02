// src/main/java/com/tu/proyecto/repositories/LibroRepository.java
package com.Libreria.repositories;

import com.Libreria.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}