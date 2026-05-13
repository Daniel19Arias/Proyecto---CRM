package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertLeads extends conexionDB {
    private String nombre,empresa,telefono,email,fuente,estado,fecha_contacto;
    PreparedStatement preparedStatement;
    public void insertLeads(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?)",
                    SchemaDB.TAB_LEADS,SchemaDB.COL_LEADS_NOM,SchemaDB.COL_LEADS_EMP,SchemaDB.COL_LEADS_TEL,SchemaDB.COL_LEADS_EMAIL,SchemaDB.COL_LEADS_FUENTE,SchemaDB.COL_LEADS_EST,SchemaDB.COL_LEADS_CONTACTO,SchemaDB.COL_LEADS_COM);
            preparedStatement = conexion.prepareStatement(SQL);
            nombre = JOptionPane.showInputDialog("Nombre");
            preparedStatement.setString(1,nombre);

            empresa = JOptionPane.showInputDialog("Empresa: ");
            preparedStatement.setString(2,empresa);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            preparedStatement.setString(3,telefono);

            email = JOptionPane.showInputDialog("Email: ");
            preparedStatement.setString(4,email);

            fuente = JOptionPane.showInputDialog("Fuente Captacion: ");
            preparedStatement.setString(5,fuente);

            estado = JOptionPane.showInputDialog("Estado: ");
            preparedStatement.setString(6,estado);

            fecha_contacto = JOptionPane.showInputDialog("Fecha Contacto: ");
            preparedStatement.setString(7,fecha_contacto);

            int id_comercial = Integer.parseInt(JOptionPane.showInputDialog("Id de Comercial: "));
            preparedStatement.setInt(8,id_comercial);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null ,"Lead insertado correctamente, Filas modificadas "+fila);
            }else {
                JOptionPane.showMessageDialog(null ,"Error al insertar lead","ERROR",JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("denied")) {
                throw new sinPermisos();
            } else {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
            }
        }finally {
            cerrarConexionBD();
        }
    }
}
