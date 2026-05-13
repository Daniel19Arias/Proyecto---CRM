package sentenciasSQL.deletes;

import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteClientes extends conexionDB {
    String id_string;
    int id;
    PreparedStatement preparedStatement;
    public void deleteClientes(){
        try {
            abrirConexionDB();
            String  SQL = String.format("DELETE FROM %s WHERE %s = ?",
                    SchemaDB.TAB_CLI,SchemaDB.COL_CLI_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            id_string = JOptionPane.showInputDialog(null, "Ingrese el id del cliente que desea eliminar: ");
            id = Integer.parseInt(id_string);
            preparedStatement.setInt(1,id);
            int filas = preparedStatement.executeUpdate();
            if (filas>0){
                JOptionPane.showMessageDialog(null, "Filas modificadas: "+filas);
            }else {
                JOptionPane.showMessageDialog(null, "Tabla no actualizada");
            }
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("denied")) {
                throw new sinPermisos();
            } else {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
            }
        }
        finally {
            cerrarConexionBD();
        }
    }
}
