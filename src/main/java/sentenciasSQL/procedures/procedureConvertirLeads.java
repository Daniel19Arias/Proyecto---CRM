package sentenciasSQL.procedures;

import database.*;
import excepciones.comprobarNif;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class procedureConvertirLeads extends conexionDB{
    PreparedStatement preparedStatement;
    public void procedureConvertirLeads() {
        try {
            String PROCEDURE = String.format("CALL %s (?,?,?)",
                    SchemaDB.PROCEDURE_CONVERTIR_LEADS);
            abrirConexionDB();
            preparedStatement = conexion.prepareStatement(PROCEDURE);
            int id =  Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el ID del lead"));
            preparedStatement.setInt(1, id);

            String nif= JOptionPane.showInputDialog(null, "Ingrese NIF del lead");
            if (!nif.matches("^[0-9]{8}[a-zA-Z]$")){
                throw new comprobarNif();
            }
            preparedStatement.setString(2, nif);

            String direccion =  JOptionPane.showInputDialog(null, "Ingrese Dirección del lead");
            preparedStatement.setString(3, direccion);

            int filas = preparedStatement.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Lead convertido a Cliente correctamente, filas moificadas "+filas,"Realizado", JOptionPane.INFORMATION_MESSAGE);
            }else  {
                JOptionPane.showMessageDialog(null, "Error al convertir Lead a Cliente","ERROR",JOptionPane.ERROR_MESSAGE );
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
