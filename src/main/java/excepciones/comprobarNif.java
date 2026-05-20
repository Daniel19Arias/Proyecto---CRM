package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (inserts, updates, etc.) que la lanzan
public class comprobarNif extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public comprobarNif() {
        // super: llama al constructor de la clase padre (RuntimeException) para registrar internamente el mensaje de error
        super("Error el NIF debe contener 8 numeros y 1 letra");
        JOptionPane.showMessageDialog(null, "Error el NIF debe contener 8 numeros y 1 letra", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
