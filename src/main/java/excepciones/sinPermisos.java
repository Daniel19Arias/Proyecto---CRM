package excepciones;

import javax.swing.*;

public class sinPermisos extends RuntimeException {

    // Ya no pedimos parámetros dentro de los paréntesis
    public sinPermisos() {
        // 1. Le pasamos el mensaje fijo a Java
        super("El usuario no tiene permiso para realizar esta accion");

        // 2. Mostramos tu mensaje fijo en la ventana
        JOptionPane.showMessageDialog(null, "El usuario no tiene permiso para realizar esta accion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}