package excepciones;

import javax.swing.*;

public class comprobarNif extends RuntimeException {
    public comprobarNif() {
        super("Error el NIF debe contener 8 numeros y 1 letra");
        JOptionPane.showMessageDialog(null,"Error el NIF debe contener 8 numeros y 1 letra", "Error",  JOptionPane.ERROR_MESSAGE);
    }
}
