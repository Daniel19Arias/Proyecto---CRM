package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.sinPermisos;

public class updateFactura extends conexionDB {

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // private: constante interna que define el único estado posible al "cobrar" una factura;
    // es un valor fijo de esta clase, no debe ser accesible ni modificable desde fuera
    private final String estado = "cobrada";

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void updateFactura() {
        try {
            abrirConexionDB();
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce el ID de la factura que quieres actualizar"));
            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_FAC, SchemaDB.COL_FAC_ESTADO, SchemaDB.COL_FAC_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setString(1, estado);
            preparedStatement.setInt(2, id);
            int filas = preparedStatement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Actualización de estado realizada con éxito. Filas modificadas: " + filas);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar actualización de estado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("denied")) {
                throw new sinPermisos();
            } else {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            cerrarConexionBD();
        }
    }
}
