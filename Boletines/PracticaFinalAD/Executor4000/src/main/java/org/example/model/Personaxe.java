package PracticaFinalAD.Executor4000.src.main.java.org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "personaxes")
public class Personaxe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpersonaxe")
    private Long id;

    @Column(name = "nome",length = 100)
    private String nome;

    @Column(name = "tipo",length = 100)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idserie")
    @JsonBackReference
    private Series serie;

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Series getSerie() {
        return serie;
    }

    public void setSerie(Series serie) {
        this.serie = serie;
    }
}
