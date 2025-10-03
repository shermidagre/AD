import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Producto p1 = new Producto("Leche", 1.5, 10);

        try (
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("serial.txt"))) {
            os.writeObject(p1);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Objeto serializado: " + p1);

    }
}