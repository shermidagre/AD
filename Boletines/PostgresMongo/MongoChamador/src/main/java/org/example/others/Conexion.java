package org.example.others;

import org.example.model.Peliculas;
import org.example.model.Actores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class Conexion {

    @Autowired
    private RestTemplate restTemplate;


    private static final String urlActores = "http://localhost:8081/api/actores";
    private static final String urlPeliculas = "http://localhost:8081/api/peliculas";

    public List<Actores> listarActores() {
        try {
            String url = urlActores;
            ResponseEntity<List<Actores>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Actores>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error al obtener actores " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Actores listarActorId(Long id) {
        try {
            String url = urlActores + "/" + id;
            ResponseEntity<Actores> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Actores.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter actores " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Actores crearActores(Actores actores) {
        try {
            String url = urlActores;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Actores> request = new HttpEntity<>(actores, headers);

            ResponseEntity<Actores> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Actores.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear actores: " + e.getMessage());
            return null;
        }
    }

    public Actores actualizarActorId(Long id, Actores datos) {
        try {
            String url = urlActores + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Actores> request = new HttpEntity<>(datos, headers);

            ResponseEntity<Actores> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Actores.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar actores " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean borrarActorId(Long id) {
        try {
            String url = urlActores + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar actores " + id + ": " + e.getMessage());
            return false;
        }
    }

    // Métodos para Peliculas

    public List<Peliculas> listarPeliculas() {
        try {
            String url = urlPeliculas;
            ResponseEntity<List<Peliculas>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Peliculas>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter peliculass: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Peliculas listarPeliculaId(Long id) {
        try {
            String url = urlPeliculas + "/" + id;
            ResponseEntity<Peliculas> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Peliculas.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter peliculas " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Peliculas crearPelicula(Peliculas peliculas) {
        try {
            String url = urlPeliculas;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Peliculas> request = new HttpEntity<>(peliculas, headers);
            ResponseEntity<Peliculas> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Peliculas.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear peliculas: " + e.getMessage());
            return null;
        }
    }

    public Peliculas actualizarPeliculaId(Long id, Peliculas datos) {
        try {
            String url = urlPeliculas + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Peliculas> request = new HttpEntity<>(datos, headers);
            ResponseEntity<Peliculas> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Peliculas.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar peliculas " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean borrarPeliculaId(Long id) {
        try {
            String url = urlPeliculas + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar peliculas " + id + ": " + e.getMessage());
            return false;
        }
    }
}
