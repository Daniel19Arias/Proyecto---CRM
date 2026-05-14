package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.comprobarCampoVacio;
import excepciones.comprobarEmail;
import excepciones.eleccionIncorrecta;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertLeads extends conexionDB {
    private String nombre,empresa,telefono,email,fuente,estado;
    protected String[] opcionesFuente = {"Seleccione una opción","Pagina Web", "Linkedin", "Feria Sectorial", "Instagram Ads", "Google Search", "Llamada en frio"};
    protected String[] opcionEstado = {"Seleccione una opción", "Nuevo","En seguimiento","Perdido"};
    PreparedStatement preparedStatement;
    public void insertLeads(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s) VALUES(?,?,?,?,?,?)",
                    SchemaDB.TAB_LEADS,SchemaDB.COL_LEADS_NOM,SchemaDB.COL_LEADS_EMP,SchemaDB.COL_LEADS_TEL,SchemaDB.COL_LEADS_EMAIL,SchemaDB.COL_LEADS_FUENTE,SchemaDB.COL_LEADS_EST);
            preparedStatement = conexion.prepareStatement(SQL);
            nombre = JOptionPane.showInputDialog("Nombre");
            if (nombre.equals("")){
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(1,nombre);

            empresa = JOptionPane.showInputDialog("Empresa: ");
            if (empresa.equals("")){
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(2,empresa);

            telefono = JOptionPane.showInputDialog("Teléfono: ");
            if (telefono.equals("")){
                throw new comprobarCampoVacio();
            }
            preparedStatement.setString(3,telefono);

            email = JOptionPane.showInputDialog("Email: ");
            if (!email.contains("@")){
                throw new comprobarEmail();
            }
            preparedStatement.setString(4,email);

            String eleccion = (String) JOptionPane.showInputDialog(null,
                    "Selecciona una fuente de captación","Fuente de Captación",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionesFuente,
                    opcionesFuente[0]);
            if (eleccion != null && eleccion != opcionesFuente[0]) {
                fuente = eleccion;
            }else {
                throw new eleccionIncorrecta();
            }
            preparedStatement.setString(5,fuente);

            String opcion = (String) JOptionPane.showInputDialog(null, "","Menú opción estado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcionEstado,
                    opcionEstado[0]);
            if (opcion != null && opcion != opcionEstado[0]) {
                estado = opcionEstado[0];
            }else {
                throw new eleccionIncorrecta();
            }
            preparedStatement.setString(6,estado);

            int fila = preparedStatement.executeUpdate();
            if (fila > 0) {
                JOptionPane.showMessageDialog(null ,"Lead insertado correctamente, Filas modificadas "+fila);
            }else {
                JOptionPane.showMessageDialog(null ,"Error al insertar lead","ERROR",JOptionPane.ERROR_MESSAGE);
            }

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
