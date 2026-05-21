package gui;

import sentenciasSQL.deletes.*;
import sentenciasSQL.inserts.*;
import sentenciasSQL.selects.*;
import sentenciasSQL.updates.*;
import sentenciasSQL.procedures.*;

import javax.swing.*;
import java.awt.Color;

public class VentanaCRUD extends JFrame {

    // private: la barra de menú y los menús son componentes visuales internos de esta ventana;
    // no hay ninguna clase externa que deba acceder a ellos directamente
    private JMenuBar menuPrincipal = new JMenuBar();
    private JMenu leads, cliente, comercial, detalle_pedido, factura, pedido, producto, interacciones, salir;

    // private: los JMenuItem son reutilizados localmente dentro del constructor para construir cada menú;
    // son referencias temporales internas, ninguna clase los usa
    private JMenuItem select, insert, delete, update, cerrarSesion, procedure;

    // private: etiqueta visual para mostrar la info de bienvenida
    // es de uso exclusivo interno y no interactúa con el exterior
    private JLabel textoFondo;

    public VentanaCRUD() {
        setTitle("CRM - AdromiTech");
        setSize(800, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        //Hover de los menús y los items
        // Gracias al UIManager de Java, que es una clase estatica(por eso no se crea objeto), sobreescribimos el azul nativo por defecto 
        // y le indicamos que al pasar el ratón (hover) use nuestro azul corporativo.
        UIManager.put("Menu.selectionBackground", Color.decode("#2D5596"));
        UIManager.put("Menu.selectionForeground", Color.WHITE);

        // Hacemos exactamente lo mismo para los desplegables interiores
        UIManager.put("MenuItem.selectionBackground", Color.decode("#2D5596"));
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        // ------------------------------------


        // Info de bienvenida--------------:
        // Se utiliza HTML dentro de un JLabel para aplicar formato (títulos, negritas, saltos de línea) 
        // sin necesidad de crear múltiples componentes de texto.
        textoFondo = new JLabel("<html><h2 style='color:#2D5596;'>Bienvenido al CRM de AdromiTech</h2>"
                + "Sistema integral de gestión diseñado para optimizar el flujo de trabajo del equipo comercial y administrativo. Centraliza todas tus operaciones en un único entorno rápido y seguro.<br><br>"
                + "<b>¿Cómo empezar a usar la plataforma?</b><br><br>"
                + "- <b>Navegación:</b> Utiliza la barra de menú superior para desplegar los módulos disponibles (Clientes, Leads, Facturas, Comerciales...).<br><br>"
                + "- <b>Operaciones:</b> Dentro de cada módulo, haz clic en la acción deseada (Listar, Insertar, Actualizar o Eliminar) para enviar la orden a la base de datos.<br><br>"
                + "- <b>Procesos Avanzados:</b> Ciertas pestañas contienen herramientas automatizadas como 'Generar Factura' o 'Convertir Lead a Cliente'.<br><br>"
                + "<i>Desarrollado y mantenido por el equipo de AdromiTech.</i></html>");
        // Se ubica en el recuadro blanco del centro
        textoFondo.setBounds(50, 20, 700, 350);
        add(textoFondo);

        leads = new JMenu("Leads");
        cliente = new JMenu("Clientes");
        comercial = new JMenu("Comerciales");
        detalle_pedido = new JMenu("Detalle de pedido");
        factura = new JMenu("Facturas");
        pedido = new JMenu("Pedidos");
        producto = new JMenu("Productos");
        interacciones = new JMenu("Interacciones");
        salir = new JMenu("Salir");

         
         // Metemos un separador entre los menús, creamos un 'MatteBorder'
         // Una herramienta de swing que te deja definir el grosor de cada lado.
        // Esto dibuja una fina línea de 2 píxel SOLO en la parte derecha de cada pestaña.
        javax.swing.border.Border separador = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.LIGHT_GRAY);
        
        leads.setBorder(separador);
        cliente.setBorder(separador);
        comercial.setBorder(separador);
        factura.setBorder(separador);
        pedido.setBorder(separador);
        producto.setBorder(separador);
        interacciones.setBorder(separador);
        salir.setBorder(separador);

        // Y ahora se añaden las opciones del menú por modulo:

        select = new JMenuItem("Listar Leads");
        insert = new JMenuItem("Insertar Leads");
        delete = new JMenuItem("Eliminar Leads");
        update = new JMenuItem("Actualizar Leads");
        procedure = new JMenuItem("Convertir Lead a Cliente");
        leads.add(select);
        leads.add(insert);
        leads.add(delete);
        leads.add(update);
        leads.add(procedure);
        menuPrincipal.add(leads);
        select.addActionListener(e -> {
            selectLeads selectLeads = new selectLeads();
            selectLeads.selectLeads();
        });
        insert.addActionListener(e -> {
            insertLeads insertLeads = new insertLeads();
            insertLeads.insertLeads();
        });
        delete.addActionListener(e -> {
            deleteLeads deleteLeads = new deleteLeads();
            deleteLeads.deleteLeads();
        });
        update.addActionListener(e -> {
            updateLeads updateLeads = new updateLeads();
            updateLeads.updateLeads();
        });
        procedure.addActionListener(e -> {
            procedureConvertirLeads procedureConvertirLeads = new procedureConvertirLeads();
            procedureConvertirLeads.procedureConvertirLeads();
        });

        // Al usar 'new', se crea un botón totalmente nuevo e independiente en la memoria.
        // La variable 'select' simplemente deja de apuntar al botón anterior (Leads) y empieza a apuntar a este nuevo.
        // El botón anterior no se borra porque ya está guardado a salvo dentro de su menú correspondiente.
        select = new JMenuItem("Listar Clientes");
        insert = new JMenuItem("Insert Cliente");
        delete = new JMenuItem("Eliminar Cliente");
        update = new JMenuItem("Actualizar Cliente");
        cliente.add(select);
        cliente.add(insert);
        cliente.add(delete);
        cliente.add(update);
        menuPrincipal.add(cliente);
        select.addActionListener(e -> {
            selectClientes selectClientes = new selectClientes();
            selectClientes.selectClientes();
        });
        insert.addActionListener(e -> {
            insertClientes insertClientes = new insertClientes();
            insertClientes.insertClientes();
        });
        delete.addActionListener(e -> {
            deleteClientes deleteClientes = new deleteClientes();
            deleteClientes.deleteClientes();
        });
        update.addActionListener(e -> {
            updateClientes updateClientes = new updateClientes();
            updateClientes.updateClientes();
        });

        select = new JMenuItem("Listar Comerciales");
        insert = new JMenuItem("Insertar Comercial");
        delete = new JMenuItem("Eliminar Comercial");
        update = new JMenuItem("Actualizar Comercial");
        comercial.add(select);
        comercial.add(insert);
        comercial.add(delete);
        comercial.add(update);
        menuPrincipal.add(comercial);
        select.addActionListener(e -> {
            selectComerciales selectComerciales = new selectComerciales();
            selectComerciales.selectComerciales();
        });
        insert.addActionListener(e -> {
            insertComerciales insertComerciales = new insertComerciales();
            insertComerciales.insertComerciales();
        });
        delete.addActionListener(e -> {
            deleteComercial deletecomercial = new deleteComercial();
            deletecomercial.deleteComercial();
        });
        update.addActionListener(e -> {
            updateComercial updateComercial = new updateComercial();
            updateComercial.updateComercial();
        });

        select = new JMenuItem("Listar Facturas");
        delete = new JMenuItem("Eliminar Factura");
        update = new JMenuItem("Cobrar Factura");
        procedure = new JMenuItem("Generar Factura");
        factura.add(select);
        factura.add(delete);
        factura.add(update);
        factura.add(procedure);
        menuPrincipal.add(factura);
        select.addActionListener(e -> {
            selectFacturas selectFacturas = new selectFacturas();
            selectFacturas.selectFacturas();
        });
        delete.addActionListener(e -> {
            deleteFacturas deleteFacturas = new deleteFacturas();
            deleteFacturas.deleteFacturas();
        });
        update.addActionListener(e -> {
            updateFactura updateFactura = new updateFactura();
            updateFactura.updateFactura();
        });
        procedure.addActionListener(e -> {
            procedureGenerarFactura procedureGenerarFactura = new procedureGenerarFactura();
            procedureGenerarFactura.procedureGenerarFactura();
        });

        select = new JMenuItem("Listar Pedidos");
        delete = new JMenuItem("Eliminar Pedido");
        pedido.add(select);
        pedido.add(delete);
        menuPrincipal.add(pedido);
        select.addActionListener(e -> {
            selectPedidos selectPedidos = new selectPedidos();
            selectPedidos.selectPedidos();
        });
        delete.addActionListener(e -> {
            deletePedido deletePedido = new deletePedido();
            deletePedido.deletePedido();
        });

        select = new JMenuItem("Listar Productos");
        insert = new JMenuItem("Insertar Producto");
        delete = new JMenuItem("Eliminar Producto");
        update = new JMenuItem("Actualizar Producto");
        producto.add(select);
        producto.add(insert);
        producto.add(delete);
        producto.add(update);
        menuPrincipal.add(producto);
        select.addActionListener(e -> {
            selectProductos selectProductos = new selectProductos();
            selectProductos.selectProductos();
        });
        insert.addActionListener(e -> {
            insertProductos insertProductos = new insertProductos();
            insertProductos.insertProductos();
        });
        delete.addActionListener(e -> {
            deleteProducto deleteProducto = new deleteProducto();
            deleteProducto.deleteProducto();
        });
        update.addActionListener(e -> {
            updateProducto updateProducto = new updateProducto();
            updateProducto.updateProducto();
        });

        select = new JMenuItem("Listar Interacciones");
        interacciones.add(select);
        menuPrincipal.add(interacciones);
        select.addActionListener(e -> {
            selectInteracciones selectInteracciones = new selectInteracciones();
            selectInteracciones.listarInteracciones();
        });

        cerrarSesion = new JMenuItem("Cerrar Sesión");
        salir.add(cerrarSesion);
        menuPrincipal.add(salir);
        cerrarSesion.addActionListener(e -> {
            VentanaLogin ventanaLogin = new VentanaLogin();
            ventanaLogin.setVisible(true);
            dispose();
        });

        setJMenuBar(menuPrincipal);
    }
}
