package org.example.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class ActoresDTO {

    private Long idActor;
    private String nome;
    private String apelidos;
    private String nacionalidade;
    @JsonBackReference
    private PeliculasDTO peliculas; // Reference back to PeliculasDTO

    // Constructors
    public ActoresDTO() {
    }

    public ActoresDTO(Long idActor, String nome, String apelidos, String nacionalidade, PeliculasDTO peliculas) {
        this.idActor = idActor;
        this.nome = nome;
        this.apelidos = apelidos;
        this.nacionalidade = nacionalidade;
        this.peliculas = peliculas;
    }

    // Getters and Setters
    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public PeliculasDTO getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(PeliculasDTO peliculas) {
        this.peliculas = peliculas;
    }
}
