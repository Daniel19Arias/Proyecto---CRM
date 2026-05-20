package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (inserts, etc.) que la lanzan
public class eleccionIncorrecta extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public eleccionIncorrecta() {
        // super: llama al constructor de la clase padre (RuntimeException) para registrar internamente el mensaje de error
        super("Error, selección de opción incorrecta");
        JOptionPane.showMessageDialog(null, "Error, selección de opción incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
