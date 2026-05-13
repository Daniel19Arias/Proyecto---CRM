package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectLeads extends conexionDB{
    PreparedStatement preparedStatement;
    public void selectLeads(){
        try{
            abrirConexionDB();
            String SQL = String.format("Select * FROM %s",
                    SchemaDB.TAB_LEADS);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            String leads="";
            while(rs.next()){
                leads += "ID: "+rs.getInt(SchemaDB.COL_LEADS_ID)+" | "+
                        "Nombre: "+rs.getString(SchemaDB.COL_LEADS_NOM)+" | "+
                        "Empresa: "+rs.getString(SchemaDB.COL_LEADS_EMP)+" | "+
                        "Teléfono: "+rs.getString(SchemaDB.COL_LEADS_TEL)+" | "+
                        "Email: "+rs.getString(SchemaDB.COL_LEADS_EMAIL)+" | "+
                        "Fuente de Captación: "+rs.getString(SchemaDB.COL_LEADS_FUENTE)+" | "+
                        "Estado: "+rs.getString(SchemaDB.COL_LEADS_EST)+" | "+
                        "Fecha Contacto: "+rs.getDate(SchemaDB.COL_LEADS_CONTACTO)+" | "+
                        "ID Comercial: "+rs.getInt(SchemaDB.COL_LEADS_COM)+"\n";
            }
            JOptionPane.showMessageDialog(null, leads);
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
