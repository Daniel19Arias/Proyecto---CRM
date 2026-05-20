package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (inserts, updates, etc.) que la lanzan
public class comprobarCampoVacio extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public comprobarCampoVacio() {
        // super: llama al constructor de la clase padre (RuntimeException) para registrar internamente el mensaje de error
        super("El campo no puede quedar vacío, cancelando acción");
        JOptionPane.showMessageDialog(null, "El campo no puede quedar vacío, cancelando acción", "Error de Formato", JOptionPane.ERROR_MESSAGE);
    }
}
