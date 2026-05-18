package excepciones;

import javax.swing.*;

public class comprobarTelefono extends RuntimeException {
    public comprobarTelefono() {
        super("Error en el formato del teléfono.");
        JOptionPane.showMessageDialog(null, "El teléfono no es válido.\nDebe contener exactamente 9 números (sin letras ni espacios).", "Error de Formato", JOptionPane.WARNING_MESSAGE);
    }
}