package excepciones;

import javax.swing.*;

public class eleccionIncorrecta extends RuntimeException {
    public eleccionIncorrecta() {
        super("Error, selección de opción incorrecta");
        JOptionPane.showMessageDialog(null,"Error, selección de opción incorrecta","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
