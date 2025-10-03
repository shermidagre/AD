package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Configura los parámetros de conexión
    private static final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static void main(String[] args) throws SQLException {
        Conexionbd();
    }
    static Connection Conexionbd() throws SQLException {
        Connection conexion = null;
        try {

            System.out.println("Intentando conectar a la base de datos...");
            conexion = DriverManager.getConnection(url, user, contraseña);

            System.out.println("Conexión exitosa a la base de datos");

        } catch (SQLException e) {
            System.out.println(e);
        }

        return conexion;
    }
}
