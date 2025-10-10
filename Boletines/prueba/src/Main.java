import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://10.0.9.139:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    final static Vehiculo p1 = new Vehiculo(1,"otros","Ford mustand",2021);
    final static Vehiculo p2 = new Vehiculo(2,"s","Tesla",2023);
    final static Vehiculo p3 = new Vehiculo(3,"civic","Honda",2020);
    final static Vehiculo p4 = new Vehiculo(4,"corvette","Chevrolet",2022);
    final static Vehiculo p5 = new Vehiculo(5,"prius","Toyota",2022);

    final static InventarioTienda i1 = new InventarioTienda(1, p1.getId(), 25000,30000,10 );
    final static InventarioTienda i2 = new InventarioTienda(2, p2.getId(), 40000,50000,12 );
    final static InventarioTienda i3 = new InventarioTienda(3, p3.getId(), 18000,22000,5 );
    final static InventarioTienda i4 = new InventarioTienda(4, p4.getId(), 60000,70000,8 );
    final static InventarioTienda i5 = new InventarioTienda(5, p5.getId(), 25000,30000,6 );



    static String ruta = "Vehiculo.xml";

    public static void main(String[] args) throws SQLException, IOException, XMLStreamException {

        exportarxml();
        //insertar();
        //serializar();
        //deserializar();
    }

    static void exportarxml() throws XMLStreamException {

        XMLOutputFactory r1 = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter esc = r1.createXMLStreamWriter(new FileWriter(ruta));
            esc.writeStartDocument("1.0");
            esc.writeStartElement("Vehiculos");

                esc.writeStartElement("Vehiculo"); // abre vehiculo 1

                esc.writeStartElement("Id");
                esc.writeCharacters(String.valueOf(p1.getId()));
                esc.writeEndElement();

                esc.writeStartElement("Modelo");
                esc.writeCharacters(p1.getModelo());
                esc.writeEndElement();

                esc.writeStartElement("Marca");
                esc.writeCharacters(p1.getMarca());
                esc.writeEndElement();

                esc.writeStartElement("ano");
                esc.writeCharacters(String.valueOf(p1.getAno()));
                esc.writeEndElement();

                esc.writeEndElement(); // cierra vehiculo

                esc.writeStartElement("Vehiculo"); // abre vehiculo 2

                esc.writeStartElement("Id");
                esc.writeCharacters(String.valueOf(p2.getId()));
                esc.writeEndElement();

                esc.writeStartElement("Modelo");
                esc.writeCharacters(p2.getModelo());
                esc.writeEndElement();

                esc.writeStartElement("Marca");
                esc.writeCharacters(p2.getMarca());
                esc.writeEndElement();

                esc.writeStartElement("ano");
                esc.writeCharacters(String.valueOf(p2.getAno()));
                esc.writeEndElement();

                esc.writeEndElement(); // cierra vehiculo2

                esc.writeStartElement("Vehiculo"); // abre vehiculo 3

                esc.writeStartElement("Id");
                esc.writeCharacters(String.valueOf(p3.getId()));
                esc.writeEndElement();

                esc.writeStartElement("Modelo");
                esc.writeCharacters(p3.getModelo());
                esc.writeEndElement();

                esc.writeStartElement("Marca");
                esc.writeCharacters(p3.getMarca());
                esc.writeEndElement();

                esc.writeStartElement("ano");
                esc.writeCharacters(String.valueOf(p3.getAno()));
                esc.writeEndElement();

                esc.writeEndElement(); // cierra vehiculo3


                esc.writeStartElement("Vehiculo"); // abre vehiculo 4

                esc.writeStartElement("Id");
                esc.writeCharacters(String.valueOf(p4.getId()));
                esc.writeEndElement();

                esc.writeStartElement("Modelo");
                esc.writeCharacters(p4.getModelo());
                esc.writeEndElement();

                esc.writeStartElement("Marca");
                esc.writeCharacters(p4.getMarca());
                esc.writeEndElement();

                esc.writeStartElement("ano");
                esc.writeCharacters(String.valueOf(p4.getAno()));
                esc.writeEndElement();

                esc.writeEndElement(); // cierra vehiculo4


                esc.writeStartElement("Vehiculo"); // abre vehiculo 5

                esc.writeStartElement("Id");
                esc.writeCharacters(String.valueOf(p5.getId()));
                esc.writeEndElement();

                esc.writeStartElement("Modelo");
                esc.writeCharacters(p5.getModelo());
                esc.writeEndElement();

                esc.writeStartElement("Marca");
                esc.writeCharacters(p5.getMarca());
                esc.writeEndElement();

                esc.writeStartElement("ano");
                esc.writeCharacters(String.valueOf(p5.getAno()));
                esc.writeEndElement();

                esc.writeEndElement(); // cierra vehiculo5

            esc.writeEndElement(); // cierra vehiculos


                esc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void insertarenvehiculo () {
        Connection conexion = null;

        try {
            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            System.out.println("Conexión exitosa a la base de datos");


            String sql = "insert into public.vehiculo values ("  + p1.getId() + ", '" +
                    p1.getModelo() + "', '" +
                    p1.getMarca() + "', " +
                    p1.getAno() + ")";

            conexion.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static void insertarenInventariotienda() {
        Connection conexion = null;

        try {
            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            System.out.println("Conexión exitosa a la base de datos");


            String sql = "insert into public.inventariotenda values ("  + p1.getId() + ", '" +
                    p1.getModelo() + "', '" +
                    p1.getMarca() + "', " +
                    p1.getAno() + ")";

            conexion.createStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void serializar() throws SQLException, IOException {

        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("serial.txt"));
            os.writeObject(p1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } System.out.println("P1 serializado : \n" + p1);


    }

    static void deserializar () throws IOException {

        Vehiculo p2 = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("serial.txt"));
            p2 = (Vehiculo) is.readObject();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Objeto deserializado = \n" + p2);

    }

    static void mostraridvehiculos (){

        System.out.println("IDS DE LOS VEHICULOS"

                + p1.getId()
                + p2.getId()
                + p3.getId()
                + p4.getId()
                + p5.getId()

        );
    }

}