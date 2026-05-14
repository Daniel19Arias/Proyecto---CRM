package excepciones;

import javax.swing.*;

public class comprobarCampoVacio extends RuntimeException {
    public comprobarCampoVacio() {
        super("El campo no puede quedar vacío, cancelando acción");
        JOptionPane.showMessageDialog(null,"El campo no puede quedar vacío, cancelando acción","Error de Formato",JOptionPane.ERROR_MESSAGE);
    }
}
