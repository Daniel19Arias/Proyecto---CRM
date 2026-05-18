package sentenciasSQL.selects;

import database.conexionDB;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;

public class selectInteracciones extends conexionDB {

    public void listarInteracciones() {
        MongoClient mongoClient = null;
        try {
            // Abrir la conexión con MongoDB usando el método que añadimos a la clase padre
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

            // Mostrar el listado en un panel sencillo (JOptionPane) como el resto de selects
            if (resultado.equals("--- LISTADO DE INTERACCIONES (MONGODB) ---\n\n")) {
                JOptionPane.showMessageDialog(null, "No hay interacciones registradas.");
            } else {
                JOptionPane.showMessageDialog(null, resultado);
            }

            cursor.close();

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
