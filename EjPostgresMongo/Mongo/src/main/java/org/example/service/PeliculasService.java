package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.ParteEnriquecida;
import org.example.repository.PeliculasRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class PeliculasService {

    private final PeliculasRepository peliculasRepo;

    public PeliculasService(PeliculasRepository peliculasRepo) {
        this.peliculasRepo = peliculasRepo;
    }

    public void crearActualizarPeliculas(ParteEnriquecida a) {
        peliculasRepo.save(a);
    }

    public void borrarPeliculases (){
        peliculasRepo.deleteAll();
    }

    public ParteEnriquecida buscarPeliculas(Long id) {
        return peliculasRepo.findById(id).orElse(null);
    }

    public List<ParteEnriquecida> buscarPeliculass() {
        return peliculasRepo.findAll();
    }

    public List<ParteEnriquecida> subirJson(){
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("data/jojo_enriquecido.json"); // Ruta de lectura
            Type tipoLista = new TypeToken<List<ParteEnriquecida>>() {}.getType();
            List<ParteEnriquecida> partes = gson.fromJson(reader, tipoLista);

            for (ParteEnriquecida parte : partes) {
                peliculasRepo.save(parte);
            }
            return partes;
        } catch (IOException e) {
            System.out.println("Fichero no encontrado. "+e.getMessage());
            return null;
        }
    }

    public boolean bajarJson(){
        List<ParteEnriquecida> ps = buscarPeliculass();
        Gson gson = new Gson();
        try (FileWriter escritor = new FileWriter("data/jojo_enriquecido.json")){ // Ruta de escritura
            String json = gson.toJson(ps);
            escritor.write(json);
            System.out.println("Datos enriquecidos guardados en data/jojo_enriquecido.json");

        } catch (IOException e) {
            System.out.println("Error. "+e.getMessage());
            return false;
        }
        return true;
    }
}