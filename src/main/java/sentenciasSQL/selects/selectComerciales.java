package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectComerciales extends conexionDB {

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void selectComerciales() {
        try {
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_COMER);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String comercial = "";
            while (rs.next()) {
                comercial += "ID: " + rs.getInt(SchemaDB.COL_COMER_ID) + " | " +
                        "Nombre comercial: " + rs.getString(SchemaDB.COL_COMER_NOM) + " | " +
                        "Apellidos: " + rs.getString(SchemaDB.COL_COMER_APE) + " | " +
                        "Email: " + rs.getString(SchemaDB.COL_COMER_EMAIL) + " | " +
                        "Teléfono: " + rs.getString(SchemaDB.COL_COMER_TELEFONO) + " | " +
                        "Zona Geográfica: " + rs.getString(SchemaDB.COL_COMER_ZONA) + " | " +
                        "Fecha Alta: " + rs.getDate(SchemaDB.COL_COMER_FECHA) + "\n";
            }
            JOptionPane.showMessageDialog(null, comercial);
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
