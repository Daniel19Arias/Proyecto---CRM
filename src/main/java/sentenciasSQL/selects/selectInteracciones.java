package sentenciasSQL.selects;

import database.conexionDB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class selectInteracciones extends conexionDB {

    // public: es el método que invoca VentanaCRUD al pulsar la opción del menú; debe ser accesible desde fuera
    public void listarInteracciones() {
        MongoClient mongoClient = null;
        try {
            // Abrir la conexión con MongoDB usando el método de la clase padre
            mongoClient = abrirConexionMongo();
            if (mongoClient == null) {
                JOptionPane.showMessageDialog(null, "No se pudo conectar a MongoDB", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener la base de datos y la colección de interacciones
            MongoDatabase database = getDatabaseMongo(mongoClient);
            MongoCollection<Document> coleccion = database.getCollection("interacciones");

            // Crear un cursor para recorrer todos los documentos
            MongoCursor<Document> cursor = coleccion.find().iterator();

            String resultado = "--- LISTADO DE INTERACCIONES (MONGODB) ---\n\n";

            // Recorrer los documentos y extraer la información
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                resultado += "ID Cliente SQL: " + doc.getInteger("id_cliente_sql") + "\n";
                resultado += "Fecha: " + doc.getString("fecha") + "\n";
                resultado += "Tipo: " + doc.getString("tipo") + "\n";
                resultado += "Comercial: " + doc.getString("comercial") + "\n";
                resultado += "Comentario: " + doc.getString("comentario") + "\n";
                resultado += "------------------------------------------\n";
            }

            cursor.close();

            // Mostrar el listado
            if (resultado.equals("--- LISTADO DE INTERACCIONES (MONGODB) ---\n\n")) {
                JOptionPane.showMessageDialog(null, "No hay interacciones registradas.");
            } else {
                // Creamos el JTextArea con el resultado recopilado
                JTextArea textArea = new JTextArea(resultado, 35, 80);
                textArea.setEditable(false);

                // Creamos el scroll con su margen limpio
                JScrollPane scroll = new JScrollPane(textArea);

                // Se lo pasamos al JOptionPane
                JOptionPane.showMessageDialog(null, scroll, "Listado de Interacciones", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar interacciones: " + e.getMessage(), "Error Mongo", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cerrar el cliente de MongoDB
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
    }
}