package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.model.Actores;
import org.example.model.Peliculas;
import org.example.repository.ActoresRepository;
import org.example.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculasService {

    @Value("${postgres.api.url}")
    private String postgresApiUrl;

    private final PeliculasRepository peliculasRepository;
    private final ActoresRepository actoresRepository;
    private final RestTemplate restTemplate;

    public PeliculasService(PeliculasRepository peliculasRepository, ActoresRepository actoresRepository, RestTemplate restTemplate) {
        this.peliculasRepository = peliculasRepository;
        this.actoresRepository = actoresRepository;
        this.restTemplate = restTemplate;
    }

    // MongoDB operations
    public void crearPeliculaMongo(Peliculas peliculas) {
        peliculasRepository.save(peliculas);
    }
    public List <Peliculas> listarPeliculasMongo() {
       return peliculasRepository.findAll();
    }
    public Optional<Actores> findActoresByIdMongo(long idActor) {
        return actoresRepository.findById(String.valueOf(idActor));
    }
    public Optional<Peliculas> listarPeliculaPorIdMongo(long idPelicula) {
        return peliculasRepository.findById(String.valueOf(idPelicula));
    }

    // PostgreSQL API interactions
    public PeliculasDTO insertMovieIntoPostgres(PeliculasDTO peliculasDTO) {
        ResponseEntity<PeliculasDTO> response = restTemplate.postForEntity(postgresApiUrl + "/peliculas", peliculasDTO, PeliculasDTO.class);
        return response.getBody();
    }

    public Optional<PeliculasDTO> getMovieFromPostgresById(Long id) {
        try {
            ResponseEntity<PeliculasDTO> response = restTemplate.getForEntity(postgresApiUrl + "/peliculas/{id}", PeliculasDTO.class, id);
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            System.err.println("Error fetching movie from PostgreSQL by ID: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<PeliculasDTO> getMovieFromPostgresByTitle(String title) {
        try {
            ResponseEntity<List<PeliculasDTO>> response = restTemplate.exchange(
                    postgresApiUrl + "/peliculas",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<PeliculasDTO>>() {});
            List<PeliculasDTO> allMovies = response.getBody();
            if (allMovies != null) {
                return allMovies.stream()
                        .filter(p -> p.getTitulo().equalsIgnoreCase(title))
                        .findFirst();
            }
            return Optional.empty();
        } catch (Exception e) {
            System.err.println("Error fetching movie from PostgreSQL by Title: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Mapping methods between MongoDB model and DTO
    public Peliculas convertToMongoPeliculas(PeliculasDTO dto) {
        Peliculas peliculas = new Peliculas();
        peliculas.setIdPelicula(dto.getIdPelicula());
        peliculas.setTitulo(dto.getTitulo());
        peliculas.setXenero(dto.getXenero());
        peliculas.setAno(dto.getAno());
        if (dto.getActores() != null) {
            peliculas.setActores(dto.getActores().stream()
                    .map(this::convertToMongoActores)
                    .collect(java.util.ArrayList::new, java.util.ArrayList::add, java.util.ArrayList::addAll));
        }
        return peliculas;
    }

    private Actores convertToMongoActores(ActoresDTO dto) {
        Actores actores = new Actores();
        actores.setIdActor(dto.getIdActor());
        actores.setNome(dto.getNome());
        actores.setApelidos(dto.getApelidos());
        actores.setNacionalidade(dto.getNacionalidade());
        // Do not set peliculas in Actores for MongoDB model to avoid circular reference
        return actores;
    }

    public PeliculasDTO convertToPostgresPeliculasDTO(Peliculas peliculas) {
        PeliculasDTO dto = new PeliculasDTO();
        dto.setIdPelicula(peliculas.getIdPelicula());
        dto.setTitulo(peliculas.getTitulo());
        dto.setXenero(peliculas.getXenero());
        dto.setAno(peliculas.getAno());
        if (peliculas.getActores() != null) {
            dto.setActores(peliculas.getActores().stream()
                    .map(this::convertToPostgresActoresDTO)
                    .collect(java.util.ArrayList::new, java.util.ArrayList::add, java.util.ArrayList::addAll));
        }
        return dto;
    }

    private ActoresDTO convertToPostgresActoresDTO(Actores actores) {
        ActoresDTO dto = new ActoresDTO();
        dto.setIdActor(actores.getIdActor());
        dto.setNome(actores.getNome());
        dto.setApelidos(actores.getApelidos());
        dto.setNacionalidade(actores.getNacionalidade());
        // The PeliculasDTO reference for the actor needs to be set when constructing the full object graph for Postgres
        return dto;
    }



    public void exportarJson(){
        List<Peliculas> peliculas = listarPeliculasMongo();
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
    public void leerJson(){
        Gson gson = new Gson();

        try {
            FileReader lector = new FileReader("C:\\Users\\samue\\Documents\\dam2\\AD\\Boletines\\PostgresMongo\\MongoChamador\\src\\main\\java\\org\\example\\json\\insert.json");
            Peliculas[] peliculas = gson.fromJson(lector, Peliculas[].class);
            for (Peliculas pelicula : peliculas) {
                crearPeliculaMongo(pelicula);
            }
            lector.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
