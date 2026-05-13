package database;

/**
 * Interfaz SchemaDB para centralizar nombres de tablas y columnas.
 */
public interface SchemaDB {
    String DB_NAME = "crmadromi";

    // Tabla Comercial
    String TAB_COMER = "comercial";
    String COL_COMER_ID = "id_comercial";
    String COL_COMER_NOM = "nombre";
    String COL_COMER_APE = "apellidos";
    String COL_COMER_EMAIL = "email";
    String COL_COMER_TELEFONO = "telefono";
    String COL_COMER_ZONA = "zona_geografica";
    String COL_COMER_FECHA = "fecha_alta";

    // Tabla Leads (Clientes Potenciales)
    String TAB_LEADS = "leads";
    String COL_LEADS_ID = "id_lead";
    String COL_LEADS_NOM = "nombre";
    String COL_LEADS_EMP = "empresa";
    String COL_LEADS_TEL = "telefono";
    String COL_LEADS_EMAIL = "email";
    String COL_LEADS_FUENTE = "fuente_captacion";
    String COL_LEADS_EST = "estado";
    String COL_LEADS_CONTACTO = "fecha_contacto";
    String COL_LEADS_COM = "id_comercial";

    // Tabla Cliente (Formales)
    String TAB_CLI = "cliente";
    String COL_CLI_ID = "id_cliente";
    String COL_CLI_RS = "razon_social";
    String COL_CLI_NIF = "nif_cif";
    String COL_CLI_DIR = "direccion_fiscal";
    String COL_CLI_CP = "contacto_principal";
    String COL_CLI_COM = "id_comercial";
    String COL_CLI_TEL = "telefono";
    String COL_CLI_EMAIL = "email";
    String COL_CLI_CONDICION = "condiciones_pago";
    String COL_CLI_DESCUENTO = "descuento_habitual";
    String COL_CLI_ESTADO = "estado";

    // Tabla Pedido
    String TAB_PED = "pedido";
    String COL_PED_ID = "id_pedido";
    String COL_PED_FECHA = "fecha_pedido";
    String COL_PED_CLI = "id_cliente";
    String COL_PED_COM = "id_comercial";
    String COL_PED_EST = "estado";

    // Tabla Factura
    String TAB_FAC = "factura";
    String COL_FAC_ID = "id_factura";
    String COL_FAC_EMISION = "fecha_emision";
    String COL_FAC_VENCIMIENTO= "fecha_vencimiento";
    String COL_FAC_BASE = "base_imponible";
    String COL_FAC_IVA = "tipo_iva";
    String COL_FAC_TOTAL = "total_factura";
    String COL_FAC_ESTADO = "estado";
    String COL_FAC_NUM = "num_factura";
    String COL_FAC_PED = "id_pedido";
    String COL_FAC_CLI = "id_cliente";

    //Tabla Producto
    String TAB_PROD = "producto";
    String COL_PROD_ID = "id_producto";
    String COL_PROD_NOMBRE = "nombre";
    String COL_PROD_DESCRIPCION = "descripcion";
    String COL_PROD_PRECIO = "precio_base";
    String COL_PROD_STOCK = "stock";

    //Procedures
    String PROCEDURE_CONVERTIR_LEADS = "ConvertirLeadACliente";
    String PROCEDURE_GENERAR_FACTURA = "GenerarFacturaDePedido";
}
