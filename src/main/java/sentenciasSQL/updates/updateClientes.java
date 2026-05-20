package sentenciasSQL.updates;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.*;
import excepciones.comprobarCampoVacio;
import excepciones.comprobarEmail;
import excepciones.comprobarTelefono;
import excepciones.sinPermisos;

public class updateClientes extends conexionDB {

    // private: el PreparedStatement es un recurso interno de la operación; no debe exponerse fuera de la clase
    private PreparedStatement preparedStatement;

    // private: variables de trabajo internas del flujo de actualización; ninguna clase externa necesita acceso a ellas
    private int id;
    private String opcionString;
    private int opcion;
    private String nuevoValor;

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void updateClientes() {
        try {
            abrirConexionDB();
            id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente que deseas modificar: "));

            opcionString = JOptionPane.showInputDialog("Que columna deseas modificar\n" +
                    "1. Razon Social\n" +
                    "2. NIF\n" +
                    "3. Dirección Fiscal\n" +
                    "4. Contacto Principal\n" +
                    "5. Teléfono\n" +
                    "6. Email\n" +
                    "7. Condiciones de pago\n" +
                    "8. Descuento habitual\n" +
                    "9. Estado"
            );
            opcion = Integer.parseInt(opcionString);

            String columnaMod = "";
            switch (opcion) {
                case 1:
                    columnaMod = SchemaDB.COL_CLI_RS;
                    break;
                case 2:
                    columnaMod = SchemaDB.COL_CLI_NIF;
                    break;
                case 3:
                    columnaMod = SchemaDB.COL_CLI_DIR;
                    break;
                case 4:
                    columnaMod = SchemaDB.COL_CLI_CP;
                    break;
                case 5:
                    columnaMod = SchemaDB.COL_CLI_TEL;
                    break;
                case 6:
                    columnaMod = SchemaDB.COL_CLI_EMAIL;
                    break;
                case 7:
                    columnaMod = SchemaDB.COL_CLI_CONDICION;
                    break;
                case 8:
                    columnaMod = SchemaDB.COL_CLI_DESCUENTO;
                    break;
                case 9:
                    columnaMod = SchemaDB.COL_CLI_ESTADO;
                    break;
            }
            nuevoValor = JOptionPane.showInputDialog("Escribe el nuevo valor");

            String SQL = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                    SchemaDB.TAB_CLI, columnaMod, SchemaDB.COL_CLI_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            if (columnaMod.equals(SchemaDB.COL_CLI_TEL)) {
                if (nuevoValor == null || !nuevoValor.matches("^[0-9]{9}$")) {
                    throw new comprobarTelefono();
                }
                preparedStatement.setString(1, nuevoValor);
            } else if (columnaMod.equals(SchemaDB.COL_CLI_EMAIL)) {
                if (nuevoValor == null || !nuevoValor.contains("@")) {
                    throw new comprobarEmail();
                }
                preparedStatement.setString(1, nuevoValor);
            } else if (nuevoValor != null) {
                preparedStatement.setString(1, nuevoValor);
            } else {
                preparedStatement.setString(1, nuevoValor);
            }
            preparedStatement.setInt(2, id);
            int filas = preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Filas modificadas " + filas);

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
