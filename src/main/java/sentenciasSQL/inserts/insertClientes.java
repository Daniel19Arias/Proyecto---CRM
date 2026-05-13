package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertClientes extends conexionDB {
    private String razon_social,nif,direccion,contacto_principal,telefono,email,condicion_pago,descuento,estado,comercial;
    PreparedStatement preparedStatement;
    public void insertClientes(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?,?,?)",
                    SchemaDB.TAB_CLI,SchemaDB.COL_CLI_RS,SchemaDB.COL_CLI_NIF,SchemaDB.COL_CLI_DIR,SchemaDB.COL_CLI_CP,SchemaDB.COL_CLI_TEL,SchemaDB.COL_CLI_EMAIL,SchemaDB.COL_CLI_CONDICION,SchemaDB.COL_CLI_DESCUENTO,SchemaDB.COL_CLI_ESTADO,SchemaDB.COL_CLI_COM);
            preparedStatement = conexion.prepareStatement(SQL);

            razon_social = JOptionPane.showInputDialog("Razon Social: ");
            preparedStatement.setString(1,razon_social);

            nif = JOptionPane.showInputDialog("NIF: ");
            preparedStatement.setString(2,nif);

            direccion = JOptionPane.showInputDialog("Dirección Fiscal: ");
            preparedStatement.setString(3,direccion);

            contacto_principal = JOptionPane.showInputDialog("Contacto Principal: ");
            preparedStatement.setString(4,contacto_principal);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            preparedStatement.setString(5,telefono);

            email = JOptionPane.showInputDialog("Email: ");
            preparedStatement.setString(6,email);

            condicion_pago = JOptionPane.showInputDialog("Condición de pago: ");
            preparedStatement.setString(7,condicion_pago);

            descuento = JOptionPane.showInputDialog("Descuento habitual: ");
            double descuento_aplicado =  Double.parseDouble(descuento);
            preparedStatement.setDouble(8,descuento_aplicado);

            estado = JOptionPane.showInputDialog("Estado de Pago: ");
            preparedStatement.setString(9,estado);

            comercial = JOptionPane.showInputDialog("ID Comercial: ");
            preparedStatement.setString(10,comercial);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null ,"Filas modificadas "+fila);
            }else {
                JOptionPane.showMessageDialog(null ,"No se pudo modificar ninguna fila");
            }

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("denied")) {
                throw new sinPermisos();
            } else {
                JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "ERROR SQL", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            cerrarConexionBD();
        }
    }
}
