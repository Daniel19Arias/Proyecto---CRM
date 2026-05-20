package sentenciasSQL.procedures;

import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class procedureGenerarFactura extends conexionDB {

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void procedureGenerarFactura() {
        try {
            String PROCEDURE = String.format("CALL %s (?)",
                    SchemaDB.PROCEDURE_GENERAR_FACTURA);
            abrirConexionDB();
            preparedStatement = conexion.prepareStatement(PROCEDURE);

            int id_pedido = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del Pedido:"));
            preparedStatement.setInt(1, id_pedido);

            int filas = preparedStatement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Factura Generado Correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error al generar Factura", "ERROR", JOptionPane.ERROR_MESSAGE);
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
