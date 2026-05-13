package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectClientes extends conexionDB{
    PreparedStatement preparedStatement;
    public void selectClientes(){
        try{
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_CLI);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String clientes="";
            while(rs.next()){
                clientes += "ID: "+rs.getInt(SchemaDB.COL_CLI_ID)+" | "+
                        "Razon Social: "+ rs.getString(SchemaDB.COL_CLI_RS)+" | "+
                        "NIF: "+ rs.getString(SchemaDB.COL_CLI_NIF)+" | "+
                        "Direccion Fiscal: "+rs.getString(SchemaDB.COL_CLI_DIR)+" | "+
                        "Contacto: "+rs.getString(SchemaDB.COL_CLI_CP)+" | "+
                        "Teléfono: "+rs.getString(SchemaDB.COL_CLI_TEL)+" | "+
                        "Email: "+rs.getString(SchemaDB.COL_CLI_EMAIL)+" | "+
                        "Condiciones de pago: "+rs.getString(SchemaDB.COL_CLI_CONDICION)+" | "+
                        "ID Comercial: "+rs.getInt(SchemaDB.COL_CLI_COM)+"\n";
            }
            JOptionPane.showMessageDialog(null, clientes);
            rs.close();
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
