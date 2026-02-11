package PracticaFinalAD.JoJosGres.src.main.java.org.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idserie")
    private Long id;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "anolanzamento")
    private int anolanzamento;

    @Column(name = "ambientacion",length = 100)
    private String ambientacion;

    @Column(name = "publico", length = 50)
    private String publico;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Personaxe> personaxes;

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getanolanzamento() {
        return anolanzamento;
    }

    public void setanolanzamento(int anolanzamento) {
        this.anolanzamento = anolanzamento;
    }

    public String getAmbientacion() {
        return ambientacion;
    }

    public void setAmbientacion(String ambientacion) {
        this.ambientacion = ambientacion;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public List<Personaxe> getPersonaxes() {
        return personaxes;
    }

    public void setPersonaxes(List<Personaxe> personaxes) {
        this.personaxes = personaxes;
    }
}
