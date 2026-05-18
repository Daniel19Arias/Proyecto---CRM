package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.sinPermisos;

public class updateFactura extends conexionDB{
    PreparedStatement preparedStatement;
    private String estado = "cobrada";
    public void updateFactura(){
        try {
            abrirConexionDB();
            int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce el ID de la factura que quieres actualizar"));
            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_FAC,SchemaDB.COL_FAC_ESTADO,SchemaDB.COL_FAC_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setString(1,estado);
            preparedStatement.setInt(2,id);
            int filas = preparedStatement.executeUpdate();
            if(filas>0) {
                JOptionPane.showMessageDialog(null, "Actualización de estado realizada con éxito. Filas modificadas: " + filas);
            }else {
                JOptionPane.showMessageDialog(null,"No se pudo realizar actualización de estado","ERROR",JOptionPane.ERROR_MESSAGE);
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
