package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.sinPermisos;

public class updateComercial extends conexionDB{
    PreparedStatement preparedStatement;
    String id;
    int id_final;
    String opcionString;
    int opcion;
    String nuevoValor;
    public void updateComercial(){
        try {
            abrirConexionDB();
            id = JOptionPane.showInputDialog("ID del comercial que deseas modificar: ");
            id_final = Integer.parseInt(id);

            opcionString = JOptionPane.showInputDialog("Que columna deseas modificar\n" +
                    "1. Nombre\n"+
                    "2. Apellidos\n"+
                    "3. Email\n"+
                    "4. Teléfono\n"+
                    "5. Zona Geográfica\n"+
                    "6. Fecha de Alta"
            );
            opcion = Integer.parseInt(opcionString);

            String columnaMod = "";
            switch (opcion) {
                case 1:
                    columnaMod = SchemaDB.COL_COMER_NOM;
                    break;
                case 2:
                    columnaMod = SchemaDB.COL_COMER_APE;
                    break;
                case 3:
                    columnaMod = SchemaDB.COL_COMER_EMAIL;
                    break;
                case 4:
                    columnaMod = SchemaDB.COL_COMER_TELEFONO;
                    break;
                case 5:
                    columnaMod = SchemaDB.COL_COMER_ZONA;
                    break;
                case 6:
                    columnaMod = SchemaDB.COL_COMER_FECHA;
                    break;
            }
            nuevoValor = JOptionPane.showInputDialog("Escribe el nuevo valor");

            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_COMER,columnaMod,SchemaDB.COL_COMER_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            preparedStatement.setString(1,nuevoValor);
            preparedStatement.setInt(2,id_final);
            int filas = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null ,"Filas modificadas "+filas);

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
