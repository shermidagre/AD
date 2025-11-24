package org.example.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
CREATE TABLE titor (
id_titor SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
apelidos VARCHAR(150) NOT NULL
);

 */
@Entity
@Table(name = "titor")
public class Titor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_titor;

    @Column(name = "nome",length = 100, nullable = false)
    private String nome;

    @Column(name = "apelidos",length = 150, nullable = false)
    private String apelidos;

    @OneToMany(mappedBy = "titor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Alumno> alumnos = new ArrayList<>();

    // Construtores
    public Titor() {
    }

    public Titor(String nome, String apelidos, String email) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    // Getters e Setters


    public Long getId_titor() {
        return id_titor;
    }

    public void setId_titor(Long id_titor) {
        this.id_titor = id_titor;
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

}
