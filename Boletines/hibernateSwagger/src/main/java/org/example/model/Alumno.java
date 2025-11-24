package org.example.model;

/*
CREATE TABLE alumno (
id_alumno SERIAL PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
apelidos VARCHAR(150) NOT NULL,
id_titor INTEGER NOT NULL,
CONSTRAINT fk_titor
FOREIGN KEY (id_titor)
REFERENCES titor (id_titor)
ON UPDATE CASCADE
ON DELETE RESTRICT
);
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_alumno;

    @Column(name = "nome",length = 100, nullable = false)
    private String nome;

    @Column(name = "apelidos",length = 150, nullable = false)
    private String apelidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "id_titor",
            referencedColumnName = "id_titor",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_titor")
    )
    private Titor titor;

    public Alumno(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    // Construtores
    public Alumno() {
    }



    // Getters y Setters


    public Long getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Long id_alumno) {
        this.id_alumno = id_alumno;
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

    public Titor getTitor() {
        return titor;
    }

    public void setTitor(Titor titor) {
        this.titor = titor;
    }
}
