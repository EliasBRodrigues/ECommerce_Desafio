package com.ecommerce.Control;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

public class JsonUtils {
    static List<Document> getJsonDocuments(){
        return Arrays.asList(
            Document.parse("{ \"nome\": \"ESPONJA\", \"preço\": 2.99, \"quantidade\": 20 }"),
            Document.parse("{ \"nome\": \"DETERGENTE\", \"preço\": 1.99, \"quantidade\": 15 }"),
            Document.parse("{ \"nome\": \"SHAMPOO\", \"preço\": 16.99, \"quantidade\": 25 }"),
            Document.parse("{ \"nome\": \"SUCO TANG\", \"preço\": 0.99, \"quantidade\": 30 }"),
            Document.parse("{ \"nome\": \"KINDER OVO\", \"preço\": 23.99, \"quantidade\": 15 }"),
            Document.parse("{ \"nome\": \"SALGADINHO\", \"preço\": 3.99, \"quantidade\": 30 }"),
            Document.parse("{ \"nome\": \"SABONETE\", \"preço\": 4.99, \"quantidade\": 20 }"),
            Document.parse("{ \"nome\": \"DANONE\", \"preço\": 8.99, \"quantidade\": 20 }"),
            Document.parse("{ \"nome\": \"ARROZ\", \"preço\": 24.99, \"quantidade\": 10 }"),
            Document.parse("{ \"nome\": \"SUCRILHOS\", \"preço\": 15.99, \"quantidade\": 15 }")
        );
    }
}
