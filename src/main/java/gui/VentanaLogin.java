package gui;

import javax.swing.*;
import database.*;

public class VentanaLogin extends JFrame {
    protected conexionDB conexion = new  conexionDB();
    private JButton bIniciarSesion,bCancelar;
    private JTextField nombre;
    private JPasswordField password;
    private JLabel name,pwd;
    private int intentos = 0;
    private final int maxIntentos = 3;
    public VentanaLogin() {
        setTitle("Iniciar Sesión - CRM ADROMI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        name = new JLabel("Nombre de usuario");
        name.setBounds(50,30,150,35);
        add(name);

        nombre = new JTextField();
        nombre.setBounds(200,30,150,35);
        add(nombre);

        pwd = new JLabel("Contraseña");
        pwd.setBounds(50,80,150,35);
        add(pwd);

        password = new JPasswordField();
        password.setBounds(200,80,150,35);
        add(password);

        bIniciarSesion = new JButton("Iniciar Sesión");
        bIniciarSesion.setBounds(30,150,150,30);
        bIniciarSesion.setFocusPainted(false);
        add(bIniciarSesion);
        bIniciarSesion.addActionListener(e -> {
            String username = nombre.getText();
            String pass = String.valueOf(password.getPassword());

            if(conexion.validarCredenciales(username, pass)) {
                intentos = 0;
                VentanaCRUD ventanaCRUD = new VentanaCRUD();
                ventanaCRUD.setVisible(true);
                dispose();
            } else {
                intentos++;
                if (intentos >= maxIntentos) {
                    JOptionPane.showMessageDialog(null, "Sin intentos\nCerrando la app", "ERROR", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos.\nIntentos restantes: " + (3 - intentos), "ERROR", JOptionPane.ERROR_MESSAGE);
                    nombre.setText("");
                    password.setText("");
                }
            }
        });

        bCancelar = new JButton("Cancelar");
        bCancelar.setBounds(200,150,150,30);
        bCancelar.setFocusPainted(false);
        add(bCancelar);
        bCancelar.addActionListener(e -> {
            System.exit(0);
        });
    }
    public static void main(String[] args) {
        VentanaLogin ventanaLogin = new VentanaLogin();
        ventanaLogin.setVisible(true);
    }
}