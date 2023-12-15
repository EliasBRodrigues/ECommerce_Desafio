package com.ecommerce.Control;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connect {
    private static final String connectionString = "mongodb://localhost:27017";
    private static final String databaseString = "Commerce";
    private static final String collectionName = "Products";

    private static final MongoClient mongoClient = MongoClients.create(connectionString);

    public static MongoCollection<Document> getCollection(){
        MongoDatabase database = mongoClient.getDatabase(databaseString);
        //verificando se a colecao e o banco de dados ja existem
        if(!collectionExists(database, collectionName)){
            database.createCollection(collectionName);
            //insert documentos na colecao, json
            try {
                List<Document> jsonDocuments = JsonUtils.getJsonDocuments();
                if(!jsonDocuments.isEmpty()){
                    database.getCollection(collectionName).insertMany(jsonDocuments);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        return database.getCollection(collectionName);
    }
    //verificar se a colecao existe
    private static boolean collectionExists(MongoDatabase database, String collectionName) {
        for (String name : database.listCollectionNames()) {
            if (name.equals(collectionName)) {
                return true;
            }
        }
        return false;
    }
    //fechar conexao com mongo
    public void closeConnection(){
        mongoClient.close();
    }
}