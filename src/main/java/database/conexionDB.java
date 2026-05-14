package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class conexionDB {
    protected Connection conexion;
    private Properties propiedades;

    // Variables estáticas para recordar quién inició sesión en toda la app
    public static String usuarioNativo;
    public static String passwordNativa;

    public conexionDB() {
        propiedades = new Properties();
        cargarPropiedades();
    }

    private void cargarPropiedades() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) propiedades.load(input);
        } catch (IOException ex) {
            System.out.println("Error al leer db.properties: " + ex.getMessage());
        }
    }

    // --- NUEVO MÉTODO DE VALIDACIÓN NATIVA ---
    public boolean validarCredenciales(String usuario, String pass) {
        try {
            // Obtenemos SOLO la URL de tu db.properties
            String url = propiedades.getProperty("mysql.url");

            // Intentamos hacer una conexión REAL a la base de datos con los datos del login
            Connection conexionPrueba = DriverManager.getConnection(url, usuario, pass);

            // Si llegamos a esta línea, la conexión fue un éxito. El usuario y clave son reales.
            conexionPrueba.close(); // Cerramos esta conexión de prueba

            // Guardamos las credenciales para usarlas en el resto del programa (Selects, Inserts, etc.)
            usuarioNativo = usuario;
            passwordNativa = pass;

            return true;

        } catch (SQLException e) {
            // Si el usuario no existe en MySQL o la contraseña está mal, MySQL rechaza la conexión
            // y entra por este catch, devolviendo false al login.
            System.out.println("Fallo de login (Acceso Denegado por MySQL): " + e.getMessage());
            return false;
        }
    }

    public void abrirConexionDB() {
        try {
            String url = propiedades.getProperty("mysql.url");

            // Ahora la conexión no usa un texto fijo, sino el usuario que se validó en el login
            conexion = DriverManager.getConnection(url, usuarioNativo, passwordNativa);
            System.out.println("Conexión abierta con el usuario: " + usuarioNativo);

        } catch (SQLException e) {
            throw new RuntimeException("Fallo al conectar a la base de datos", e);
        }
    }

    public void cerrarConexionBD() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }

    // --- MÉTODOS PARA MONGODB ---

    public MongoClient abrirConexionMongo() {
        try {
            String uri = propiedades.getProperty("mongo.uri");
            return MongoClients.create(uri);
        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
            return null;
        }
    }

    public MongoDatabase getDatabaseMongo(MongoClient client) {
        String dbName = propiedades.getProperty("mongo.db");
        return client.getDatabase(dbName);
    }
}