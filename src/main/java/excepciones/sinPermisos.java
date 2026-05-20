package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (deletes, inserts, selects, updates, procedures) que la lanzan
public class sinPermisos extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public sinPermisos() {
        // Le pasamos el mensaje fijo a Java
        // super: llama al constructor de la clase padre (RuntimeException) para registrar internamente el mensaje de error
        super("El usuario no tiene permiso para realizar esta accion");

        // Mostramos el mensaje de error en pantalla
        JOptionPane.showMessageDialog(null, "El usuario no tiene permiso para realizar esta accion", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}