package org.example;

import org.example.dto.ActoresDTO;
import org.example.dto.PeliculasDTO;
import org.example.model.Actores;
import org.example.model.Peliculas;
import org.example.service.PeliculasMongoService;
import org.example.service.PostgresApiClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Secuencia {

    private final PostgresApiClientService postgresApiClientService;
    private final PeliculasMongoService peliculasMongoService;

    @Autowired
    public Secuencia(PostgresApiClientService postgresApiClientService, PeliculasMongoService peliculasMongoService) {
        this.postgresApiClientService = postgresApiClientService;
        this.peliculasMongoService = peliculasMongoService;
    }

    public void ejecuccionSecuencia() {
        System.out.println("Iniciando secuencia de operaciones...");

        // 1. Insertar datos de 2 películas con 3 actores cada una en PostgreSQL.
        System.out.println("1. Insertando películas con actores en PostgreSQL...");

        // Pelicula 1 DTO
        PeliculasDTO pelicula1DTO = new PeliculasDTO();
        pelicula1DTO.setTitulo("El Señor de los Anillos: La Comunidad del Anillo");
        pelicula1DTO.setXenero("Fantasia");
        pelicula1DTO.setAno(2001);

        ActoresDTO actor1_1_DTO = new ActoresDTO(null, "Elijah", "Wood", "Estadounidense", pelicula1DTO);
        ActoresDTO actor1_2_DTO = new ActoresDTO(null, "Ian", "McKellen", "Británico", pelicula1DTO);
        ActoresDTO actor1_3_DTO = new ActoresDTO(null, "Orlando", "Bloom", "Británico", pelicula1DTO);

        pelicula1DTO.setActores(Arrays.asList(actor1_1_DTO, actor1_2_DTO, actor1_3_DTO));


        // Pelicula 2 DTO
        PeliculasDTO pelicula2DTO = new PeliculasDTO();
        pelicula2DTO.setTitulo("Pulp Fiction");
        pelicula2DTO.setXenero("Crimen");
        pelicula2DTO.setAno(1994);

        ActoresDTO actor2_1_DTO = new ActoresDTO(null, "John", "Travolta", "Estadounidense", pelicula2DTO);
        ActoresDTO actor2_2_DTO = new ActoresDTO(null, "Uma", "Thurman", "Estadounidense", pelicula2DTO);
        ActoresDTO actor2_3_DTO = new ActoresDTO(null, "Samuel L.", "Jackson", "Estadounidense", pelicula2DTO);

        pelicula2DTO.setActores(Arrays.asList(actor2_1_DTO, actor2_2_DTO, actor2_3_DTO));

        PeliculasDTO createdPelicula1DTO = postgresApiClientService.insertPelicula(pelicula1DTO);
        PeliculasDTO createdPelicula2DTO = postgresApiClientService.insertPelicula(pelicula2DTO);
        System.out.println("Películas insertadas en PostgreSQL.");

        // 2. Leer los datos de una película, consultándola por id, con todos sus actores en postgres
        //    e insertarla en mongodb.
        System.out.println("2. Leyendo película por ID de PostgreSQL e insertando en MongoDB...");
        if (createdPelicula1DTO != null && createdPelicula1DTO.getIdPelicula() != null) {
            Optional<PeliculasDTO> peliculaByIdDTO = postgresApiClientService.getPeliculaById(createdPelicula1DTO.getIdPelicula());
            peliculaByIdDTO.ifPresent(dto -> {
                Peliculas peliculaMongo = convertToMongoPeliculas(dto);
                peliculasMongoService.save(peliculaMongo);
                System.out.println("Película '" + peliculaMongo.getTitulo() + "' (ID: " + peliculaMongo.getIdPelicula() + ") insertada en MongoDB.");
            });
        } else {
            System.out.println("No se pudo obtener la película creada por ID de PostgreSQL.");
        }


        // 3. Leer los datos de una película, consultándola por nombre, con todos sus actores en postgres
        //    e insertarla en mongodb.
        System.out.println("3. Leyendo película por nombre de PostgreSQL e insertando en MongoDB...");
        Optional<PeliculasDTO> peliculaByTitleDTO = postgresApiClientService.getPeliculaByTitulo("Pulp Fiction");
        peliculaByTitleDTO.ifPresent(dto -> {
            Peliculas peliculaMongo = convertToMongoPeliculas(dto);
            peliculasMongoService.save(peliculaMongo);
            System.out.println("Película '" + peliculaMongo.getTitulo() + "' (ID: " + peliculaMongo.getIdPelicula() + ") insertada en MongoDB.");
        });

        // 4. Leer los datos de películas en mongodb, con sus actores, y exportarlas en un archivo JSON
        System.out.println("4. Exportando películas de MongoDB a JSON...");
        peliculasMongoService.exportToJsonFile("insert.json");
        System.out.println("Películas exportadas a insert.json.");

        System.out.println("Secuencia de operaciones completada.");
    }

    private Peliculas convertToMongoPeliculas(PeliculasDTO dto) {
        Peliculas peliculas = new Peliculas();
        peliculas.setIdPelicula(dto.getIdPelicula());
        peliculas.setTitulo(dto.getTitulo());
        peliculas.setXenero(dto.getXenero());
        peliculas.setAno(dto.getAno());
        if (dto.getActores() != null) {
            List<Actores> actores = dto.getActores().stream()
                    .map(this::convertToMongoActores)
                    .collect(Collectors.toList());
            peliculas.setActores(actores);
        }
        return peliculas;
    }

    private Actores convertToMongoActores(ActoresDTO dto) {
        Actores actores = new Actores();
        actores.setIdActor(dto.getIdActor());
        actores.setNome(dto.getNome());
        actores.setApelidos(dto.getApelidos());
        actores.setNacionalidade(dto.getNacionalidade());
        return actores;
    }
}