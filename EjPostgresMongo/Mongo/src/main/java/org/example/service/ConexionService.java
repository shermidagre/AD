package org.example.service;

import org.example.dto.ParteDTO;
import org.example.dto.PersonajeDTO;
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

    private static final String JOJO_BASE_URL_PARTES = "http://localhost:8080/jojo/partes";
    private static final String JOJO_BASE_URL_PERSONAJES = "http://localhost:8080/jojo/personajes";

    // ========== PARTES ==========
    public List<ParteDTO> getAllPartes() {
        try {
            ResponseEntity<List<ParteDTO>> response = restTemplate.exchange(
                    JOJO_BASE_URL_PARTES, HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener partes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public ParteDTO getParteById(Long id) {
        try {
            ResponseEntity<ParteDTO> response = restTemplate.exchange(
                    JOJO_BASE_URL_PARTES + "/" + id, HttpMethod.GET, null, ParteDTO.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al obtener parte " + id + ": " + e.getMessage());
            return null;
        }
    }
    
    public ParteDTO createParte(ParteDTO parte) {
        try {
            HttpEntity<ParteDTO> request = new HttpEntity<>(parte, new HttpHeaders());
            ResponseEntity<ParteDTO> response = restTemplate.exchange(
                    JOJO_BASE_URL_PARTES, HttpMethod.POST, request, ParteDTO.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al crear parte: " + e.getMessage());
            return null;
        }
    }

    // ========== PERSONAJES ==========
    public PersonajeDTO createPersonaje(PersonajeDTO personaje) {
        try {
            HttpEntity<PersonajeDTO> request = new HttpEntity<>(personaje, new HttpHeaders());
            ResponseEntity<PersonajeDTO> response = restTemplate.exchange(
                    JOJO_BASE_URL_PERSONAJES, HttpMethod.POST, request, PersonajeDTO.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("Error al crear personaje: " + e.getMessage());
            return null;
        }
    }
}
