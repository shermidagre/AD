import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) {

       String ruta = "autores.xml";

        // Parte 1

        Producto p1 = new Producto("Leche", 1, 10);

        try (
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("serial.txt"))) {
            os.writeObject(p1);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Objeto serializado: " + p1);

        Producto p2 = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("serial.txt"));
            p2 = (Producto) is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Objeto deserializado: " + p2);


        // Parte 2


        ProductoTransient pt1 = new ProductoTransient("Pan", 1, 20);
        try (
                ObjectOutputStream ostr = new ObjectOutputStream(new FileOutputStream("transient.txt"))) {
            ostr.writeObject(pt1);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        System.out.println("Objeto serializado con transient: " + pt1);
        ProductoTransient pt2 = null;
        try {
            ObjectInputStream istr = new ObjectInputStream(new FileInputStream("transient.txt"));
            pt2 = (ProductoTransient) istr.readObject();
            istr.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        System.out.println("Objeto serializado con transient: " + pt2);


        // Parte 3

        XMLOutputFactory r1 = XMLOutputFactory.newInstance();
        try {
         XMLStreamWriter esc =  r1.createXMLStreamWriter(new FileWriter(ruta));
         esc.writeStartDocument("1.0");

         esc.writeStartElement("autores");

            esc.writeStartElement("autor");
            esc.writeAttribute("codigo", "a1");


            esc.writeStartElement("nombre");
            esc.writeCharacters("Alexandre Dumas");
            esc.writeEndElement();

            esc.writeStartElement("titulo");
            esc.writeCharacters("El conde de montecristo");
            esc.writeEndElement();

            esc.writeStartElement("titulo");
            esc.writeCharacters("Los miserables");
            esc.writeEndElement();


            esc.writeEndElement(); // autor

            esc.writeStartElement("autor");
            esc.writeAttribute("codigo", "a2");

            esc.writeStartElement("nombre");
            esc.writeCharacters("Fiodor Dosyevski");
            esc.writeEndElement();

            esc.writeStartElement("titulo");
            esc.writeCharacters("Noches blancas");
            esc.writeEndElement();
            esc.writeStartElement("titulo");
            esc.writeCharacters("El idiota");
            esc.writeEndElement();


            esc.writeEndElement(); // autor
            esc.writeEndElement(); // autores


            esc.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("XML creado en: " + ruta);


    }
}

