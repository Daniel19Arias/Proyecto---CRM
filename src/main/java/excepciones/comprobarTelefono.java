package excepciones;

import javax.swing.*;

// public: la clase debe ser visible desde todos los paquetes (inserts, updates, etc.) que la lanzan
public class comprobarTelefono extends RuntimeException {

    // public: el constructor de una excepción debe ser public para que pueda instanciarse con 'throw new ...' desde cualquier paquete
    public comprobarTelefono() {
        super("Error en el formato del teléfono.");
        JOptionPane.showMessageDialog(null, "El teléfono no es válido.\nDebe contener exactamente 9 números (sin letras ni espacios).", "Error de Formato", JOptionPane.WARNING_MESSAGE);
    }
}