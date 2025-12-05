package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "titor")
public class Titor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id_titor;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "apelidos", length = 150, nullable = false)
    private String apelidos;

    // JsonIgnore para evitar recursión infinita al serializar Titor -> Alumnos -> Titor
    @JsonIgnore
    @OneToMany(mappedBy = "titor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos = new ArrayList<>();

    public Titor() {}

    public Titor(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    public Long getId_titor() { return id_titor; }
    public void setId_titor(Long id_titor) { this.id_titor = id_titor; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelidos() { return apelidos; }
    public void setApelidos(String apelidos) { this.apelidos = apelidos; }

    public List<Alumno> getAlumnos() { return alumnos; }
    public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }
}