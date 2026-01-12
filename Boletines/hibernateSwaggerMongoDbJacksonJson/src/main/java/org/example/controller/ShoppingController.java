package org.example.controller;

import org.example.model.Shopping;
import org.example.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/import")
    public Shopping importFile(@RequestParam String path) throws IOException {
        // Ejemplo de uso: POST http://localhost:8080/api/shopping/import?path=C:/datos/lista.json
        return shoppingService.importShoppingList(path);
    }
}