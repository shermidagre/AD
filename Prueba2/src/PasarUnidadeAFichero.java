import java.io.*;
import java.sql.SQLException;

public class PasarUnidadeAFichero {
    static void serializar() throws SQLException, IOException {

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("serial.txt"));
            os.writeObject(Main.unidade1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } System.out.println("Unidade serializado : \n" + Main.unidade1);


    }

    static void deserializar () throws IOException {

        Unidade p2 = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("serial.txt"));
            p2 = (Unidade) is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Objeto deserializado = \n" + p2);

    }
}
