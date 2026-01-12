package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.model.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("runner") // Importante: Solo se carga en el microservicio de secuencia
public class Secuence {

    @Autowired
    private ShoppingService shoppingService;

    /**
     * Este método se ejecuta automáticamente al arrancar el microservicio de secuencia.
     */
    @PostConstruct
    public void pruebas() {
        // Ruta del archivo (ajusta según tu sistema)
        String path = "src/main/java/org/example/json/shopp.json";
        Shopping data = null;

        System.out.println("===============================================================");
        System.out.println("INICIANDO SECUENCIA DE EJERCICIOS");
        System.out.println("===============================================================");

        // --- EXERCICIO 1: Carga de Datos ---
        System.out.println("\nExecución Exercicio 1: Importación de archivo JSON");
        try {
            data = shoppingService.importShoppingList(path);
            System.out.println("Éxito: Datos cargados y guardados en MongoDB.");
        } catch (Exception e) {
            System.err.println("Fallo en Exercicio 1: " + e.getMessage());
        }
        System.out.println("FIN Exercicio 1");

        // --- EXERCICIO 2: Visualización de Groceries ---
        System.out.println("\nExecución Exercicio 2: Detalles de Comestibles (Groceries)");
        if (data != null && data.getShoppingList().getGroceries() != null) {
            data.getShoppingList().getGroceries().forEach(item ->
                    System.out.println(" > Item: " + item.getItems() + " | Qty: " + item.getQuantity() + " " + item.getUnit()));
        }
        System.out.println("FIN Exercicio 2");

        // --- EXERCICIO 3: Resumen de Cuidado Personal ---
        System.out.println("\nExecución Exercicio 3: Conteo de items de Personal Care");
        if (data != null) {
            int totalItems = data.getShoppingList().getPersonalCare().size();
            System.out.println("Total de productos de cuidado personal: " + totalItems);
        }
        System.out.println("FIN Exercicio 3");

        System.out.println("\n===============================================================");
        System.out.println("SECUENCIA COMPLETADA. CERRANDO SISTEMA...");
        System.out.println("===============================================================");


        System.exit(0);
    }
}