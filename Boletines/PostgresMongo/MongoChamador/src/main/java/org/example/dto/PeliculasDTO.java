package org.example.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

public class PeliculasDTO {

    private Long idPelicula;
    private String titulo;
    private String xenero;
    private int ano;
    @JsonManagedReference
    private List<ActoresDTO> actores;

    // Constructors
    public PeliculasDTO() {
    }

    public PeliculasDTO(Long idPelicula, String titulo, String xenero, int ano, List<ActoresDTO> actores) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.xenero = xenero;
        this.ano = ano;
        this.actores = actores;
    }

    // Getters and Setters
    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getXenero() {
        return xenero;
    }

    public void setXenero(String xenero) {
        this.xenero = xenero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<ActoresDTO> getActores() {
        return actores;
    }

    public void setActores(List<ActoresDTO> actores) {
        this.actores = actores;
    }
}
