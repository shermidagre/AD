package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "actores")
public class Actores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActor")
    private Long idActor;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "apelidos", length = 100, nullable = false)
    private String apelidos;

    @Column(name = "nacionalidade", length = 100, nullable = false)
    private String nacionalidade;


    @ManyToOne
    @JoinColumn(name = "idPelicula") // FK a equipos(id_equipo)
    @JsonBackReference
    private Peliculas peliculas;

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

    public Peliculas getEquipo() {
        return peliculas;
    }

    public void setEquipo(Peliculas peliculas) {
        this.peliculas = peliculas;
    }
}
