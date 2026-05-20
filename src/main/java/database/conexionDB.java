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

    // protected: las subclases (inserts, selects, etc.) necesitan acceder a 'conexion' directamente para preparar statements
    protected Connection conexion;

    // private: solo esta clase carga y gestiona las propiedades; ninguna subclase necesita acceder a ellas
    private Properties propiedades;

    // public static: son las credenciales globales de sesión que deben ser accesibles desde cualquier clase del programa
    public static String usuarioNativo;
    public static String passwordNativa;

    public conexionDB() {
        propiedades = new Properties();
        cargarPropiedades();
    }

    // private: método auxiliar interno que solo llama el constructor; no debe ser accesible ni sobreescrito
    private void cargarPropiedades() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input != null) propiedades.load(input);
        } catch (IOException ex) {
            System.out.println("Error al leer db.properties: " + ex.getMessage());
        }
    }

    // public: debe ser llamado desde VentanaLogin para validar el usuario al iniciar sesión
    public boolean validarCredenciales(String usuario, String pass) {
        try {
            String url = propiedades.getProperty("mysql.url");
            Connection conexionPrueba = DriverManager.getConnection(url, usuario, pass);
            conexionPrueba.close();
            usuarioNativo = usuario;
            passwordNativa = pass;
            return true;
        } catch (SQLException e) {
            System.out.println("Fallo de login (Acceso Denegado por MySQL): " + e.getMessage());
            return false;
        }
    }

    // public: lo llaman todas las subclases (inserts, selects, deletes, updates, procedures) al inicio de cada operación
    public void abrirConexionDB() {
        try {
            String url = propiedades.getProperty("mysql.url");
            conexion = DriverManager.getConnection(url, usuarioNativo, passwordNativa);
            System.out.println("Conexión abierta con el usuario: " + usuarioNativo);
        } catch (SQLException e) {
            throw new RuntimeException("Fallo al conectar a la base de datos", e);
        }
    }

    // public: lo llaman todas las subclases en el bloque finally para cerrar la conexión al terminar
    public void cerrarConexionBD() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar: " + e.getMessage());
        }
    }

    // public: lo usa selectInteracciones (subclase) para abrir la conexión con MongoDB
    public MongoClient abrirConexionMongo() {
        try {
            String uri = propiedades.getProperty("mongo.uri");
            return MongoClients.create(uri);
        } catch (Exception e) {
            System.out.println("Error al conectar a MongoDB: " + e.getMessage());
            return null;
        }
    }

    // public: lo usa selectInteracciones para obtener la base de datos MongoDB una vez abierto el cliente
    public MongoDatabase getDatabaseMongo(MongoClient client) {
        String dbName = propiedades.getProperty("mongo.db");
        return client.getDatabase(dbName);
    }
}