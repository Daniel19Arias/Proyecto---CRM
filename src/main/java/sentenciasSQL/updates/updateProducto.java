package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.sinPermisos;

public class updateProducto extends conexionDB{
    PreparedStatement preparedStatement;
    String id;
    int id_final;
    String opcionString;
    int opcion;
    String nuevoValor;
    public void updateProducto(){
        try {
            abrirConexionDB();
            id = JOptionPane.showInputDialog("ID del producto que deseas modificar: ");
            id_final = Integer.parseInt(id);

            opcionString = JOptionPane.showInputDialog("Que columna deseas modificar\n" +
                    "1. Nombre\n" +
                    "2. Descripción\n" +
                    "3. Precio base\n" +
                    "4. Stock"
            );
            opcion = Integer.parseInt(opcionString);

            String columnaMod = "";
            switch (opcion){
                case 1:
                    columnaMod = SchemaDB.COL_PROD_NOMBRE;
                    break;
                case 2:
                    columnaMod = SchemaDB.COL_PROD_DESCRIPCION;
                    break;
                case 3:
                    columnaMod = SchemaDB.COL_PROD_PRECIO;
                    break;
                case 4:
                    columnaMod = SchemaDB.COL_PROD_STOCK;
                    break;
            }
            nuevoValor = JOptionPane.showInputDialog("Escribe el nuevo valor");

            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_PROD,columnaMod,SchemaDB.COL_PROD_ID);
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
