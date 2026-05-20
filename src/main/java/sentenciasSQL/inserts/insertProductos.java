package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertProductos extends conexionDB {

    // private: datos que el usuario introduce para este insert; solo los gestiona esta clase
    private String nombre, descripcion, precio_base, stock;

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void insertProductos() {
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES(?,?,?,?)",
                    SchemaDB.TAB_PROD, SchemaDB.COL_PROD_NOMBRE, SchemaDB.COL_PROD_DESCRIPCION, SchemaDB.COL_PROD_PRECIO, SchemaDB.COL_PROD_STOCK);
            preparedStatement = conexion.prepareStatement(SQL);

            nombre = JOptionPane.showInputDialog("Nombre del producto: ");
            preparedStatement.setString(1, nombre);

            descripcion = JOptionPane.showInputDialog("Descripción: ");
            preparedStatement.setString(2, descripcion);

            precio_base = JOptionPane.showInputDialog("Precio Base: ");
            preparedStatement.setString(3, precio_base);

            stock = JOptionPane.showInputDialog("Stock: ");
            preparedStatement.setString(4, stock);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null, "Filas modificadas " + fila);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo modificar ninguna fila");
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
