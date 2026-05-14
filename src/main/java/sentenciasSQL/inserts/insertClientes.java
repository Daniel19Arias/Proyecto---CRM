package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertClientes extends conexionDB {
    private String razon_social,nif,direccion,contacto_principal,telefono,email,condicion_pago,descuento;
    protected String[] condiciones = {"Selecciona una opción","contado","30 días","60 días","90 días"};
    PreparedStatement preparedStatement;
    public void insertClientes(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?,?,?)",
                    SchemaDB.TAB_CLI,SchemaDB.COL_CLI_RS,SchemaDB.COL_CLI_NIF,SchemaDB.COL_CLI_DIR,SchemaDB.COL_CLI_CP,SchemaDB.COL_CLI_TEL,SchemaDB.COL_CLI_EMAIL,SchemaDB.COL_CLI_CONDICION,SchemaDB.COL_CLI_DESCUENTO);
            preparedStatement = conexion.prepareStatement(SQL);

            razon_social = JOptionPane.showInputDialog("Razon Social: ");
            if (razon_social.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(1,razon_social);

            nif = JOptionPane.showInputDialog("NIF: ");
            if (!nif.matches("^[0-9]{8}[a-zA-Z]$")){
                throw new comprobarNif();
            }
            preparedStatement.setString(2,nif);

            direccion = JOptionPane.showInputDialog("Dirección Fiscal: ");
            if (direccion.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(3,direccion);

            contacto_principal = JOptionPane.showInputDialog("Contacto Principal: ");
            if (contacto_principal.equals("")) {
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(4,contacto_principal);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            if (telefono == null || !telefono.matches("^[0-9]{9}$")) {
                throw new comprobarTelefono();
            }
            preparedStatement.setString(5,telefono);

            email = JOptionPane.showInputDialog("Email: ");
            if (!email.contains("@")){
                throw new  comprobarEmail();
            }
            preparedStatement.setString(6,email);

            String opciones = (String) JOptionPane.showInputDialog(null,"","Condiciones de pago",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    condiciones,
                    condiciones[0]
                    );
            if (opciones != null && opciones != condiciones[0]) {
                condicion_pago = opciones;
            }else {
                throw new eleccionIncorrecta();
            }
            preparedStatement.setString(7,condicion_pago);

            descuento = JOptionPane.showInputDialog("Descuento habitual: ");
            if (descuento.equals("")) {
                throw new comprobarCampoVacio();
            }
            double descuento_aplicado =  Double.parseDouble(descuento);
            preparedStatement.setDouble(8,descuento_aplicado);

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
