package org.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "peliculas")
public class Peliculas {

    private long idPelicula;
    private String titulo;
    private String genero;
    private int año;
    private List<Actores> actores;


    // Getters y Setters


    public long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<Actores> getActores() {
        return actores;
    }

    public void setActores(List<Actores> actores) {
        this.actores = actores;
    }
}
