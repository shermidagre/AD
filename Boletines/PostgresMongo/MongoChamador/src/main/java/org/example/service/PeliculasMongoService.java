package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Peliculas;
import org.example.repository.PeliculasMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class PeliculasMongoService {

    private final PeliculasMongoRepository peliculasMongoRepository;
    private final Gson gson;

    @Autowired
    public PeliculasMongoService(PeliculasMongoRepository peliculasMongoRepository) {
        this.peliculasMongoRepository = peliculasMongoRepository;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Peliculas save(Peliculas peliculas) {
        return peliculasMongoRepository.save(peliculas);
    }

    public List<Peliculas> findAll() {
        return peliculasMongoRepository.findAll();
    }

    public void exportToJsonFile(String filePath) {
        List<Peliculas> peliculas = peliculasMongoRepository.findAll();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(peliculas, writer);
        } catch (IOException e) {
            System.err.println("Error al exportar películas a JSON: " + e.getMessage());
        }
    }
}
