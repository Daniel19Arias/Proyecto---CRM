package excepciones;

import javax.swing.*;

public class comprobarEmail extends RuntimeException {
    public comprobarEmail() {
        super("El email introducido no es válido por falta de '@'.");
        JOptionPane.showMessageDialog(null, "El email introducido no es válido.\nDebe contener obligatoriamente el símbolo '@'.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
    }
}