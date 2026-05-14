package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.comprobarCampoVacio;
import excepciones.comprobarEmail;
import excepciones.comprobarTelefono;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertComerciales extends conexionDB {
    private String nombre,apellidos,email,telefono,zona_geografica;
    PreparedStatement preparedStatement;
    public void insertComerciales(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s) VALUES(?,?,?,?,?)",
                    SchemaDB.TAB_COMER,SchemaDB.COL_COMER_NOM,SchemaDB.COL_COMER_APE,SchemaDB.COL_COMER_EMAIL,SchemaDB.COL_COMER_TELEFONO,SchemaDB.COL_COMER_ZONA);
            preparedStatement = conexion.prepareStatement(SQL);
            nombre = JOptionPane.showInputDialog("Nombre: ");
            if (nombre.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(1,nombre);

            apellidos = JOptionPane.showInputDialog("Apellidos: ");
            if (apellidos.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(2,apellidos);

            email = JOptionPane.showInputDialog("Email: ");
            if (!email.contains("@")){
                throw new comprobarEmail();
            }
            preparedStatement.setString(3,email);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            if (telefono == null || !telefono.matches("^[0-9]{9}$")) {
                throw new comprobarTelefono();
            }
            preparedStatement.setString(4,telefono);

            zona_geografica = JOptionPane.showInputDialog("Zona Geográfica: ");
            if (zona_geografica.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(5,zona_geografica);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null ,"Comercial insertado correctamente\nFilas modificadas: "+fila);
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
