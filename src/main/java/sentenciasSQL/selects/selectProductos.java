package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectProductos extends conexionDB {

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void selectProductos() {
        try {
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_PROD);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String producto = "";
            while (rs.next()) {
                producto += "ID: " + rs.getInt(SchemaDB.COL_PROD_ID) + " | " +
                        "Nombre: " + rs.getString(SchemaDB.COL_PROD_NOMBRE) + " | " +
                        "Descripción: " + rs.getString(SchemaDB.COL_PROD_DESCRIPCION) + " | " +
                        "Precio Base: " + rs.getDouble(SchemaDB.COL_PROD_PRECIO) + " | " +
                        "Stock: " + rs.getInt(SchemaDB.COL_PROD_STOCK) + "\n";
            }
            JOptionPane.showMessageDialog(null, producto);
            rs.close();
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
