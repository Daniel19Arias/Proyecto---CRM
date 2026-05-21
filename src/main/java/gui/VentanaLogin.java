package gui;

import javax.swing.*;
import java.awt.Color;
import database.*;

public class VentanaLogin extends JFrame {

    // Se pone protected para que si se crease una subclase de VentanaLogin pudiera reutilizar la conexión
    protected conexionDB conexion = new conexionDB();

    // private: componentes visuales propios de esta ventana; ninguna clase externa ni subclase debe manipularlos
    private JButton bIniciarSesion, bCancelar;
    private JTextField nombre;
    private JPasswordField password;
    private JLabel name, pwd;

    // private: el contador de intentos es estado interno de esta ventana; no debe exponerse fuera
    private int intentos = 0;

    // private final: constante interna de esta ventana; no tiene sentido que sea visible desde fuera
    private final int maxIntentos = 3;

    public VentanaLogin() {
        setTitle("Iniciar Sesión - CRM ADROMI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        name = new JLabel("Nombre de usuario");
        name.setBounds(50, 30, 150, 35);
        add(name);

        nombre = new JTextField();
        nombre.setBounds(200, 30, 150, 35);
        add(nombre);

        pwd = new JLabel("Contraseña");
        pwd.setBounds(50, 80, 150, 35);
        add(pwd);

        password = new JPasswordField();
        password.setBounds(200, 80, 150, 35);
        add(password);

        bIniciarSesion = new JButton("Iniciar Sesión");
        bIniciarSesion.setBounds(30, 150, 150, 30);
        bIniciarSesion.setFocusPainted(false);
        
        // Colores de nuestro CRM
        bIniciarSesion.setBackground(Color.decode("#2D5596"));
        bIniciarSesion.setForeground(Color.WHITE);
        
        
        add(bIniciarSesion);
        bIniciarSesion.addActionListener(e -> {
            String username = nombre.getText();
            String pass = String.valueOf(password.getPassword());

            if (conexion.validarCredenciales(username, pass)) {
                intentos = 0;
                VentanaCRUD ventanaCRUD = new VentanaCRUD();
                ventanaCRUD.setVisible(true);
                dispose();
            } else {
                intentos++;
                if (intentos >= maxIntentos) {
                    // SE USA EL MÉTODO SOBRECARGADO: Pasamos el mensaje y 'true' para cerrar la aplicación
                    mostrarError("Sin intentos\nCerrando la app", true);
                } else {
                    // SE USA EL MÉTODO NORMAL: Pasamos solo el mensaje, muestra el error pero deja continuar
                    mostrarError("Nombre de usuario o contraseña incorrectos.\nIntentos restantes: " + (3 - intentos));
                    nombre.setText("");
                    password.setText("");
                }
            }
        });

        bCancelar = new JButton("Cancelar");
        bCancelar.setBounds(200, 150, 150, 30);
        bCancelar.setFocusPainted(false);
        
        // --- Diseño corporativo ---
        bCancelar.setBackground(Color.decode("#2D5596"));
        bCancelar.setForeground(Color.WHITE);
        // --------------------------
        
        add(bCancelar);
        bCancelar.addActionListener(e -> {
            System.exit(0);
        });
    }

    // public: es el punto de entrada de la aplicación; debe ser accesible
    public static void main(String[] args) {
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
    }

    // --- Aplicación de sobrecarga---
    
    // Método normal: Muestra un mensaje de error en pantalla sin cerrar el programa
    public void mostrarError(String mensaje) {
        // null: indica que la ventana se centrará en la pantalla al no tener un componente padre asociado
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    // Método sobrecargado: Hace lo mismo pero recibe un boolean adicional.
    // Si 'cerrarApp' es true, además de mostrar el error, finaliza la ejecución del programa.
    public void mostrarError(String mensaje, boolean cerrarApp) {
        // null: centra la ventana en la pantalla
        JOptionPane.showMessageDialog(null, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
        if (cerrarApp) {
            System.exit(0);
        }
    }
    // -------------------------------------------
}