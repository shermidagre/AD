package org.example.dto;

// DTO para recibir datos de Personajes desde el servidor Postgres
public class PersonajeDTO {
    private Long id;
    private String nombre;
    private String nacionalidad;
    private ParteDTO parte;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public ParteDTO getParte() { return parte; }
    public void setParte(ParteDTO parte) { this.parte = parte; }
}
