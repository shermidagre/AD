package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Actores;
import org.example.model.Peliculas;
import org.example.repository.ActoresRepository;
import org.example.repository.PeliculasRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculasService {

    private PeliculasRepository peliculasRepository;
    private ActoresRepository actoresRepository;
    private ObjectMapper objectMapper;

    public PeliculasService(PeliculasRepository peliculasRepository, ActoresRepository actoresRepository, ObjectMapper objectMapper) {
        this.peliculasRepository = peliculasRepository;
        this.actoresRepository = actoresRepository;
        this.objectMapper = objectMapper;
    }

    public void crearPelicula(Peliculas peliculas) {
        peliculasRepository.save(peliculas);
    }
    public List <Peliculas> listarPeliculas() {
       return peliculasRepository.findAll();
    }
    public Optional<Actores> listarPeliculasPorActor(long idActor) {
        return actoresRepository.findById(String.valueOf(idActor));
    }
    public Optional<Peliculas> listarPeliculaPorId(long idPelicula) {
        return peliculasRepository.findById(String.valueOf(idPelicula));
    }

    public void exportarJson(){
        List<Peliculas> peliculas = listarPeliculas();
        Gson gson = new Gson();

        try {
            FileWriter escritor = new FileWriter("C:\\Users\\samue\\Documents\\dam2\\AD\\Boletines\\PostgresMongo\\MongoChamador\\src\\main\\java\\org\\example\\json\\insert.json");
            String json = gson.toJson(peliculas);
            escritor.write(json);
            escritor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void leerCrearJson(){

        try {
            FileReader lector = new FileReader("C:\\Users\\samue\\Documents\\dam2\\AD\\Boletines\\PostgresMongo\\MongoChamador\\src\\main\\java\\org\\example\\json\\insert.json");
            Peliculas[] peliculas = gson.fromJson(lector, Peliculas[].class);
            for (Peliculas pelicula : peliculas) {
                crearPelicula(pelicula);
            }
            lector.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
