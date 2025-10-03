import java.io.Serializable;

public class ProductoTransient implements Serializable {
     transient String nombre;
     transient double precio;
     int cantidad;

    public ProductoTransient(String nombre, double precio, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ProductoTransient " +
                "nombre='" + nombre + '\'' +
                ",Transient precio=" + precio +
                ",cantidad=" + cantidad
                ;
    }
}
