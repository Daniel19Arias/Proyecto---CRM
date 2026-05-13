package sentenciasSQL.inserts;

import javax.swing.*;
import database.*;
import excepciones.sinPermisos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertPedido extends conexionDB {
    private String fecha_pedido, estado, id_cliente, id_pedido;
    PreparedStatement preparedStatement;
    public void insertPedido(){
        try {
            abrirConexionDB();
            String SQL = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES(?,?,?,?)",
                    SchemaDB.TAB_PED,SchemaDB.COL_PED_FECHA,SchemaDB.COL_PED_EST,SchemaDB.COL_PED_CLI,SchemaDB.COL_PED_ID);
            preparedStatement = conexion.prepareStatement(SQL);
            fecha_pedido = JOptionPane.showInputDialog("Fecha de pedido: ");
            preparedStatement.setString(1,fecha_pedido);

            estado = JOptionPane.showInputDialog("Estado: ");
            preparedStatement.setString(2,estado);

            id_cliente = JOptionPane.showInputDialog("ID Cliente: ");
            preparedStatement.setString(3,id_cliente);

            id_pedido = JOptionPane.showInputDialog("ID Pedido: ");
            preparedStatement.setString(4,id_pedido);

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
