package org.example.service;

import org.example.model.Equipo;
import org.example.model.Xogador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ConexionService {

    @Autowired
    private RestTemplate restTemplate;


    private static final String POSTGRES_BASE_URL_XOGADORES = "http://localhost:8081/postgres/xogadores";
    private static final String POSTGRES_BASE_URL_EQUIPOS = "http://localhost:8081/postgres/equipos";
    // ========== XOGADORES ==========
    public List<Xogador> getAllXogadores() {
        try {
            String url = POSTGRES_BASE_URL_XOGADORES;
            ResponseEntity<List<Xogador>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Xogador>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter xogadores: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Xogador getXogadorById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_XOGADORES + "/" + id;
            ResponseEntity<Xogador> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Xogador.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter xogador " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Xogador createXogador(Xogador xogador) {
        try {
            String url = POSTGRES_BASE_URL_XOGADORES;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Xogador> request = new HttpEntity<>(xogador, headers);

            ResponseEntity<Xogador> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Xogador.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear xogador: " + e.getMessage());
            return null;
        }
    }

    public Xogador updateXogador(Long id, Xogador datos) {
        try {
            String url = POSTGRES_BASE_URL_XOGADORES + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Xogador> request = new HttpEntity<>(datos, headers);

            ResponseEntity<Xogador> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Xogador.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar xogador " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean deleteXogador(Long id) {
        try {
            String url = POSTGRES_BASE_URL_XOGADORES + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar xogador " + id + ": " + e.getMessage());
            return false;
        }
    }

    //EQUIPO
    public List<Equipo> getAllEquipos() {
        try {
            String url = POSTGRES_BASE_URL_EQUIPOS;
            ResponseEntity<List<Equipo>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Equipo>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter equipos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Equipo getEquipoById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_EQUIPOS + "/" + id;
            ResponseEntity<Equipo> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Equipo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao obter equipo " + id + ": " + e.getMessage());
            return null;
        }
    }

    public Equipo createEquipo(Equipo equipo) {
        try {
            String url = POSTGRES_BASE_URL_EQUIPOS;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Equipo> request = new HttpEntity<>(equipo, headers);
            ResponseEntity<Equipo> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Equipo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao crear equipo: " + e.getMessage());
            return null;
        }
    }

    public Equipo updateEquipo(Long id, Equipo datos) {
        try {
            String url = POSTGRES_BASE_URL_EQUIPOS + "/" + id;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Equipo> request = new HttpEntity<>(datos, headers);
            ResponseEntity<Equipo> response = restTemplate.exchange(
                    url, HttpMethod.PUT, request, Equipo.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao actualizar equipo " + id + ": " + e.getMessage());
            return null;
        }
    }

    public boolean deleteEquipo(Long id) {
        try {
            String url = POSTGRES_BASE_URL_EQUIPOS + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error ao borrar equipo " + id + ": " + e.getMessage());
            return false;
        }
    }
}
