import java.io.Serializable;

public class Producto implements Serializable {
    String nombre;
    double precio;
    int cantidad;

    public Producto (String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto " +
                "nombre='" + nombre + '\'' +
                ",precio=" + precio +
                ",cantidad=" + cantidad
                ;
    }

}
