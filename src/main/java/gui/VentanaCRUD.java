package gui;

import sentenciasSQL.deletes.*;
import sentenciasSQL.inserts.*;
import sentenciasSQL.selects.*;
import sentenciasSQL.updates.*;
import sentenciasSQL.procedures.*;

import javax.swing.*;

public class VentanaCRUD extends JFrame {
    protected JMenuBar menuPrincipal =  new JMenuBar();
    protected JMenu leads,cliente,comercial,detalle_pedido,factura,pedido,producto,interacciones,salir;
    protected JMenuItem select,insert,delete,update,cerrarSesion,procedure;
    public VentanaCRUD(){
        setTitle("CRM - AdromiTech");
        setSize(600, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        leads = new JMenu("Leads");
        cliente =  new JMenu("Clientes");
        comercial =  new JMenu("Comerciales");
        detalle_pedido =  new JMenu("Detalle de pedido");
        factura = new JMenu("Facturas");
        pedido = new JMenu("Pedidos");
        producto = new JMenu("Productos");
        interacciones = new JMenu("Interacciones");
        salir = new JMenu("Salir");

        select = new  JMenuItem("Listar Leads");
        insert  = new JMenuItem("Insertar Leads");
        delete  = new JMenuItem("Eliminar Leads");
        update  = new JMenuItem("Actualizar Leads");
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
           deleteComercial deletecomercial = new  deleteComercial();
           deletecomercial.deleteComercial();
        });
        update.addActionListener(e -> {
            updateClientes updateClientes = new updateClientes();
            updateClientes.updateClientes();
        });

        select =  new JMenuItem("Listar Facturas");
        delete = new JMenuItem("Eliminar Factura");
        update = new JMenuItem("Cobrar Factura");
        procedure = new  JMenuItem("Generar Factura");
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
           updateProducto updateProducto = new  updateProducto();
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
            dispose();        });

        setJMenuBar(menuPrincipal);
    }
}
