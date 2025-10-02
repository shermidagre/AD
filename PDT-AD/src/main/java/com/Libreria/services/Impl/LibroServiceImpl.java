// src/main/java/com/tu/proyecto/impl/LibroServiceImpl.java
package com.Libreria.services.Impl;

import com.Libreria.services.interfaces.LibroService;
import com.Libreria.models.Libro;
import com.Libreria.repositories.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {

    private static final Logger logger = LoggerFactory.getLogger(LibroServiceImpl.class);

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Libro guardarLibro(Libro libro) {
        logger.info("🚀 Iniciando guardado del libro: {}", libro);
        Libro libroGuardado = libroRepository.save(libro);
        logger.info("✅ Libro guardado exitosamente: {}", libroGuardado);
        return libroGuardado;
    }
}