package Serializacion_e_XML.src;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class p1 {

    public void main(String[] args) {

        Producto p1 = new Producto("Leche", 1.5, 10);

        try (
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(p1.ruta))) {
            os.writeObject(p1);
        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }
}
