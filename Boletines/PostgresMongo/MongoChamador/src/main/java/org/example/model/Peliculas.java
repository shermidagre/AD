package org.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "peliculas")
public class Peliculas {

    private long idPelicula;
    private String titulo;
    private String xenero;
    private int ano;
    private List<Actores> actores;


    // Getters y Setters


    public long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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

    public List<Actores> getActores() {
        return actores;
    }

    public void setActores(List<Actores> actores) {
        this.actores = actores;
    }
}
