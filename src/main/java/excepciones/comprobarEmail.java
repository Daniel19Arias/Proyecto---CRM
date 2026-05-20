package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (inserts, updates, etc.) que la lanzan
public class comprobarEmail extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public comprobarEmail() {
        super("El email introducido no es válido por falta de '@'.");
        JOptionPane.showMessageDialog(null, "El email introducido no es válido.\nDebe contener obligatoriamente el símbolo '@'.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
    }
}