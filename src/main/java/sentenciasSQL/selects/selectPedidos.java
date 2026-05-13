package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectPedidos extends conexionDB{
    PreparedStatement preparedStatement;
    public void selectPedidos(){
        try{
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_PED);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String pedidos="";
            while(rs.next()){
                pedidos += "ID: "+rs.getInt(SchemaDB.COL_PED_ID)+" | "+
                        "Fecha Pedido: "+rs.getDate(SchemaDB.COL_PED_FECHA)+" | "+
                        "Estado: "+rs.getString(SchemaDB.COL_PED_EST)+" | "+
                        "ID cliente: "+rs.getInt(SchemaDB.COL_PED_CLI)+" | "+
                        "ID Comercial: "+rs.getInt(SchemaDB.COL_PED_COM)+"\n";
            }
            JOptionPane.showMessageDialog(null, pedidos);
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
