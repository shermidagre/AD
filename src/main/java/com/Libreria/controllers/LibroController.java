// src/main/java/com/tu/proyecto/controllers/LibroController.java
package com.Libreria.controllers;

import com.Libreria.services.interfaces.LibroService;
import com.Libreria.models.Libro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private static final Logger logger = LoggerFactory.getLogger(LibroController.class);

    @Autowired
    private LibroService libroService;

    @PostMapping("/crear")
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        logger.info("📥 Recibido request para crear libro: {}", libro);

        try {
            Libro libroGuardado = libroService.guardarLibro(libro);
            logger.info("📤 Respondiendo con libro creado: {}", libroGuardado);
            return ResponseEntity.ok(libroGuardado);
        } catch (Exception e) {
            logger.error("❌ Error al crear libro", e);
            return ResponseEntity.status(500).build();
        }
    }
}