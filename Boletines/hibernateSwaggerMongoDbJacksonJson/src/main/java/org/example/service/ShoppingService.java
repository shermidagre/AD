package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Shopping;
import org.example.repository.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Shopping importShoppingList(String filePath) throws IOException {
        // 1. Leer el archivo JSON y mapear a la clase Shopping
        File file = new File(filePath);
        Shopping shopping = objectMapper.readValue(file, Shopping.class);

        // 2. Guardar en MongoDB usando el Repository
        return shoppingRepository.save(shopping);
    }
}