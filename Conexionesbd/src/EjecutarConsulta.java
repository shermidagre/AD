import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class EjecutarConsulta(String sql, String mensajeExito) {

    final String url = "jdbc:postgresql://10.0.2.15:5432/probas";
    final String user = "postgres";
    final String contraseña = "admin";

    try (Connection conn = DriverManager.getConnection(url, user, contraseña);
         Statement stmt = conn.createStatement()) {
        String sql;
        stmt.execute(sql);
        System.out.println(mensajeExito);
    } catch (SQLException e) {
        System.err.println(" Error al ejecutar SQL: " + e.getMessage());
    }

}