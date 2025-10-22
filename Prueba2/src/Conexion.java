import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static void main(String[] args) {
        Conexion.conectar();
    }
    private static final String url = "jdbc:postgresql://10.0.9.126:5432/probas";
    private static final String user = "postgres";
    private static final String contraseña = "admin";

    public static Connection conectar() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, contraseña);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }

}
