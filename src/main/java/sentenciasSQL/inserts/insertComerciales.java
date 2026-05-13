package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertComerciales extends conexionDB {
    private String nombre,apellidos,email,telefono,zona_geografica,fecha_alta;
    PreparedStatement preparedStatement;
    public void insertComerciales(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?)",
                    SchemaDB.TAB_COMER,SchemaDB.COL_COMER_NOM,SchemaDB.COL_COMER_APE,SchemaDB.COL_COMER_EMAIL,SchemaDB.COL_COMER_TELEFONO,SchemaDB.COL_COMER_ZONA,SchemaDB.COL_COMER_FECHA);
            preparedStatement = conexion.prepareStatement(SQL);
            nombre = JOptionPane.showInputDialog("Nombre: ");
            preparedStatement.setString(1,nombre);

            apellidos = JOptionPane.showInputDialog("Apellidos: ");
            preparedStatement.setString(2,apellidos);

            email = JOptionPane.showInputDialog("Email: ");
            preparedStatement.setString(3,email);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            preparedStatement.setString(4,telefono);

            zona_geografica = JOptionPane.showInputDialog("Zona Geográfica: ");
            preparedStatement.setString(5,zona_geografica);

            fecha_alta = JOptionPane.showInputDialog("Fecha de alta: ");
            preparedStatement.setString(6,fecha_alta);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null ,"Filas modificadas "+fila);
            }else {
                JOptionPane.showMessageDialog(null ,"No se pudo modificar ninguna fila");
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
