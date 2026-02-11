package org.example.service;

import org.example.dto.PeliculasDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PostgresApiClientService {

    @Value("${postgres.api.url}")
    private String postgresApiUrl;

    private final RestTemplate restTemplate;

    public PostgresApiClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PeliculasDTO insertPelicula(PeliculasDTO peliculasDTO) {
        String url = postgresApiUrl + "/postgres/equipos";
        return restTemplate.postForObject(url, peliculasDTO, PeliculasDTO.class);
    }

    public Optional<PeliculasDTO> getPeliculaById(Long id) {
        String url = postgresApiUrl + "/postgres/equipos/" + id;
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, PeliculasDTO.class));
        } catch (Exception e) {
            System.err.println("Error al obtener película por ID desde PostgreSQL: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<PeliculasDTO> getPeliculaByTitulo(String titulo) {
        String url = postgresApiUrl + "/postgres/equipos/titulo/" + titulo;
        try {
            return Optional.ofNullable(restTemplate.getForObject(url, PeliculasDTO.class));
        } catch (Exception e) {
            System.err.println("Error al obtener película por título desde PostgreSQL: " + e.getMessage());
            return Optional.empty();
        }
    }
}
