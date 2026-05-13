package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectFacturas extends conexionDB{
    PreparedStatement preparedStatement;
    public void selectFacturas(){
        try{
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_FAC);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String facturas="";
            while(rs.next()){
                facturas += "ID: "+rs.getInt(SchemaDB.COL_FAC_ID)+" | "+
                        "Num Factura: "+rs.getString(SchemaDB.COL_FAC_NUM)+" | "+
                        "Fecha Emision: "+rs.getDate(SchemaDB.COL_FAC_EMISION)+" | "+
                        "Fecha Vencimiento: "+ rs.getDate(SchemaDB.COL_FAC_VENCIMIENTO)+" | "+
                        "ID Pedido: "+rs.getInt(SchemaDB.COL_FAC_PED)+" | "+
                        "ID Cliente: "+rs.getInt(SchemaDB.COL_FAC_CLI)+" | "+
                        "Base Imponible: "+rs.getDouble(SchemaDB.COL_FAC_BASE)+ " | "+
                        "Tipo IVA: "+rs.getDouble(SchemaDB.COL_FAC_IVA)+ " | "+
                        "Total factura: "+rs.getDouble(SchemaDB.COL_FAC_TOTAL)+" | "+
                        "Estado: "+ rs.getString(SchemaDB.COL_FAC_ESTADO)+"\n";
            }
            JOptionPane.showMessageDialog(null, facturas);
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
