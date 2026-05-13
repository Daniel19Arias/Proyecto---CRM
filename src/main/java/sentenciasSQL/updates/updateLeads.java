package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.sinPermisos;

public class updateLeads extends conexionDB{
    PreparedStatement preparedStatement;
    String id;
    int id_final;
    String opcionString;
    int opcion;
    String nuevoValor;
    public void updateLeads(){
        try {
            abrirConexionDB();
            id = JOptionPane.showInputDialog("ID del lead que deseas modificar: ");
            id_final = Integer.parseInt(id);

            opcionString = JOptionPane.showInputDialog("Que columna deseas modificar\n" +
                    "1. Nombre\n"+
                    "2. Empresa\n"+
                    "3. Teléfono\n"+
                    "4. Email\n"+
                    "5. Fuente de Captación\n"+
                    "6. Fecha de contacto\n"+
                    "7. ID Comercial\n"
            );
            opcion = Integer.parseInt(opcionString);

            String columnaMod = "";
            switch (opcion) {
                case 1:
                    columnaMod = SchemaDB.COL_LEADS_NOM;
                    break;
                case 2:
                    columnaMod = SchemaDB.COL_LEADS_EMP;
                    break;
                case 3:
                    columnaMod = SchemaDB.COL_LEADS_TEL;
                    break;
                case 4:
                    columnaMod = SchemaDB.COL_LEADS_EMAIL;
                    break;
                case 5:
                    columnaMod = SchemaDB.COL_LEADS_FUENTE;
                    break;
                case 6:
                    columnaMod = SchemaDB.COL_LEADS_CONTACTO;
                    break;
                case 7:
                    columnaMod = SchemaDB.COL_LEADS_COM;
            }
            nuevoValor = JOptionPane.showInputDialog("Escribe el nuevo valor");

            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_LEADS,columnaMod,SchemaDB.COL_LEADS_ID);
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
