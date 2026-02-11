package org.example.model;

// DTO para el personaje con su Stand
public class PersonajeConStand {
    private Long id;
    private String nombre;
    private String nacionalidad;
    private Stand stand;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public Stand getStand() { return stand; }
    public void setStand(Stand stand) { this.stand = stand; }

    @Override
    public String toString() {
        return "PersonajeConStand{"
                + "id=" + id + 
                ", nombre='" + nombre + "'" + 
                ", nacionalidad='" + nacionalidad + "'" + 
                ", stand=" + stand + 
                '}';
    }
}
