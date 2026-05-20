package sentenciasSQL.deletes;

import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteClientes extends conexionDB {

    // private: variables de trabajo internas de este método; no las usa ninguna subclase ni clase externa
    private String id_string;
    private int id;

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void deleteClientes() {
        try {
            abrirConexionDB();
            String SQL = String.format("DELETE FROM %s WHERE %s = ?",
                    SchemaDB.TAB_CLI, SchemaDB.COL_CLI_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            // null: al no pasarle un JFrame padre, la ventana emergente se centrará en medio del monitor
            id_string = JOptionPane.showInputDialog(null, "Ingrese el id del cliente que desea eliminar: ");
            id = Integer.parseInt(id_string);
            preparedStatement.setInt(1, id);
            int filas = preparedStatement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Filas modificadas: " + filas);
            } else {
                JOptionPane.showMessageDialog(null, "Tabla no actualizada");
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
