package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Shopping;
import org.example.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Lee un JSON desde una ruta, lo convierte a objeto Shopping y lo persiste en Mongo.
     */
    public Shopping importShoppingList(String filePath) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Archivo no encontrado en: " + filePath);
            }

            // Deserialización Jackson: JSON -> Objeto Java
            Shopping shopping = objectMapper.readValue(file, Shopping.class);

            // Persistencia en MongoDB
            return shoppingRepository.save(shopping);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Error al procesar el JSON: " + e.getMessage());
        }
    }
}