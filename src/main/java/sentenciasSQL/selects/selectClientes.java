package sentenciasSQL.selects;
import database.*;
import excepciones.sinPermisos;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectClientes extends conexionDB {
    PreparedStatement preparedStatement;

    public void selectClientes() {
        try {
            abrirConexionDB();
            String SQL = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s", 
                    SchemaDB.COL_CLI_ID, SchemaDB.COL_CLI_RS, SchemaDB.COL_CLI_NIF, 
                    SchemaDB.COL_CLI_CP, SchemaDB.COL_CLI_TEL, SchemaDB.COL_CLI_EMAIL, 
                    SchemaDB.COL_CLI_ESTADO, SchemaDB.TAB_CLI);
            preparedStatement = conexion.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();

            // Definir las columnas de la tabla 
            String[] columnas = {
                "ID", "Razón Social", "NIF", "Contacto", "Teléfono", "Email", "Estado"
            };

            //Crear el modelo de la tabla. Mete el array de las columnas de arriba al modelo. El 0 es el numero de filas iniciales, ponemos 0 porque se rellenan despues
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
                public boolean isCellEditable(int row, int col) {
                    return false;
                } // esto hace que no se pueda editar ningun campo de la tabla, si no se pusiese, se editaria
            };

            // Rellenar el modelo con los datos del ResultSet
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt(SchemaDB.COL_CLI_ID),
                    rs.getString(SchemaDB.COL_CLI_RS),
                    rs.getString(SchemaDB.COL_CLI_NIF),
                    rs.getString(SchemaDB.COL_CLI_CP),
                    rs.getString(SchemaDB.COL_CLI_TEL),
                    rs.getString(SchemaDB.COL_CLI_EMAIL),
                    rs.getString(SchemaDB.COL_CLI_ESTADO)
                });
            }

            // Crear la tabla y aplicar estilos visuales
            JTable tabla = new JTable(modelo);
            tabla.setRowHeight(35);
            tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            tabla.setGridColor(new Color(230, 230, 230));
            tabla.setShowVerticalLines(false); // Estilo moderno sin líneas verticales
            tabla.setSelectionBackground(new Color(220, 237, 255)); // Color azul claro al seleccionar
            tabla.setSelectionForeground(Color.BLACK);

            //Estilizar el encabezado de la tabla
            JTableHeader header = tabla.getTableHeader();
            header.setFont(new Font("Segoe UI", Font.BOLD, 13));
            header.setBackground(new Color(45, 85, 150)); // Azul corporativo
            header.setForeground(Color.WHITE);
            header.setPreferredSize(new Dimension(0, 40));

            // Crear la ventana (JFrame) para mostrar la tabla
            JFrame ventana = new JFrame("Listado de clientes");
            ventana.setSize(1100, 600); // Tamaño amplio para ver todos los campos
            ventana.setLocationRelativeTo(null);
            
            // Envolver la tabla en un scroll con margen
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
            //se crea un borde blanco alrededor de la tabla para q no se vea pegada a los bordes del panel
            
            ventana.add(scroll);
            ventana.setVisible(true);

            rs.close();
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
