package PracticaFinalAD.Executor4000.src.main.java.org.example.service;

import PracticaFinalAD.MongoChamador.src.main.java.org.example.model.Series;
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


    private static final String urlSeries = "http://localhost:8081/postgres/series";
    private static final String urlPersonaxes = "http://localhost:8081/postgres/personaxes";

    // Recoger datos

    public List<Series> findAll() {
        try {
            String url = ConexionService.urlSeries;
            ResponseEntity<List<Series>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Series>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // Por id

    public Series searchById(Long id) {
        try {
            String url = ConexionService.urlSeries + "/" + id;
            ResponseEntity<Series> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Series.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("NonNonNon non dixeche-la palabra maxica jajaja jajaja " + e.getMessage());
            return null;
        }
    }

    // por titulo

    public Series getByTitulo(String titulo) {
        try {
            String url = urlSeries+"/titulo/"+titulo;
            HttpEntity<List<Series>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Series>>() {});
            List<Series> s = response.getBody();
            return s.get(0);
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

    // insertar todos

    public Series insertAll(Series series) {
        try {
            String url = ConexionService.urlSeries ;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Series> request = new HttpEntity<>(series, headers);

            ResponseEntity<Series> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Series.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro xenerico: " + e.getMessage());
            return null;
        }
    }

    // borrar por id

    public boolean deleteById(Long id) {
        try {
            String url = ConexionService.urlSeries + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return false;
        }
    }

}
