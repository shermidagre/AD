package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Main {
    private final Secuencia secuencia;


    public Main(Secuencia secuencia) {
        this.secuencia = secuencia;
    }
    @PostConstruct
    public void ejecuccionSecuencia() {
        secuencia.ejecuccionSecuencia();
        System.exit(200);
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}