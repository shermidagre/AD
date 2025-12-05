package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id_alumno;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "apelidos", length = 150, nullable = false)
    private String apelidos;

    @ManyToOne(optional = false, fetch = FetchType.EAGER) // Eager carga el titor al pedir el alumno
    @JoinColumn(name = "id_titor", nullable = false)
    @JsonIgnore // Ignoramos el objeto completo al serializar para evitar bucles
    private Titor titor;

    // Constructor vacío obligatorio para JPA
    public Alumno() {}

    public Alumno(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }

    // --- Getters y Setters ---

    public Long getId_alumno() { return id_alumno; }
    public void setId_alumno(Long id_alumno) { this.id_alumno = id_alumno; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelidos() { return apelidos; }
    public void setApelidos(String apelidos) { this.apelidos = apelidos; }

    public Titor getTitor() { return titor; }

    // Importante: Setter para pasar la entidad real
    public void setTitor(Titor titor) {
        this.titor = titor;
    }

    @JsonProperty("id_titor") // Para que salga en el JSON
    public Long getIdTitor() {
        return (titor != null) ? titor.getId_titor() : null;
    }

    @JsonProperty("id_titor") // Para recibirlo del JSON
    public void setIdTitor(Long idTitor) {
        if (this.titor == null) {
            this.titor = new Titor();
        }
        this.titor.setId_titor(idTitor);
    }
}
