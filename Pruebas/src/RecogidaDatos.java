import javax.xml.transform.Source;
import java.sql.SQLException;

public class RecogidaDatos {


    public void recogerDatos() {
        // Asumiendo que SÍ tienes la información mínima para conectar,
        // y quieres recoger datos de una TABLA que asumes existe.
        String sql = "SELECT * FROM usuario"; // Cambia esto.

        // Inicializar recursos a null para el bloque finally
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexion = establecerConexion();
            if (conexion == null) {
                return; // No se pudo conectar
            }

            statement = conexion.createStatement();

            // 2. Ejecutar la consulta SQL
            resultSet = statement.executeQuery(sql);

            // 3. Procesar los resultados
            // ----------------------------------------------------
            // **Recuperación de datos sin saber los nombres de columna**
            // Usamos metadatos para obtener el número de columnas y sus nombres.
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Imprimir cabeceras (nombres de columna)
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getMetaData().getColumnLabel(i) + "\t|\t");
            }
            System.out.println("\n----------------------------------------------------");

            // Imprimir los datos
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    // Usamos getObject(i) que funciona para la mayoría de tipos de datos.
                    System.out.print(resultSet.getObject(i) + "\t|\t");
                }
                System.out.println();
            }
            // ----------------------------------------------------

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta o procesar los resultados.");
            e.printStackTrace();
        } finally {
            // 4. Cerrar los recursos en orden inverso de apertura (RS, Statement, Connection)
            cerrarRecursos(resultSet, statement, conexion);
        }
    }

}
