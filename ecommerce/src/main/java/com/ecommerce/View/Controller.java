package com.ecommerce.View;

import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.Control.Connect;
import com.ecommerce.Model.Product;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;

public class Controller {
    //mostrar estoque de produtos
    public static void showProduct(List<Product> products){
        for(Product product:products){
            System.out.println(product.toString());
        }
    }
    //digitar e encontrar produtos no terminal
    public static Product findProductName(List<Product> products, String nome){
        System.out.println("PRODUTOS" + nome);
        for(Product product:products){
            if(product.getName().equalsIgnoreCase(nome)){
                System.out.println("PRODUTO ENCONTRADO" + product);
                return product;
            }
        }
        System.out.println("NENHUM PRODUTO ENCONTRADO");
        return null;
    }
    //listar produtos no terminal
    public static List<Product> listProducts(MongoCollection<Document> collection){
        List<Product> products = new ArrayList<>();
        MongoIterable<Document> documents = collection.find();
        for(Document document : documents){
            ObjectId id = document.getObjectId("_id");
            String nome = document.getString("nome");
            double preco = document.getDouble("preço");
            Integer quantidade = document.getInteger("quantidade");
            Product product = new Product(id.toString(), nome, preco, quantidade);
            products.add(product);
        }
        return products;
    }
    //caluclar valor final a ser pago
    public static double valueAllCart(List<Product> cart){
        double totalCart = 0;
        for(Product product: cart){
            totalCart += product.getPrice();
        }
        return totalCart;
    }
    //atualizar compras no carrinho
    public static void updateProduct(MongoCollection<Document> collection, String productId){
        if(productId != null){
            Document query = new Document("_id", new ObjectId(productId));
            Document update = new Document("$inc", new Document("quantidade", -1));
            collection.updateOne(query, update);
        } else {
            System.out.println("ERROR: ID NULO");
        }
    }
    //executar aplicacao
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            MongoCollection<Document> collection = Connect.getCollection();
            List<Product> products = listProducts(collection);
            System.out.println("PRODUTOS:");
            showProduct(products);
            List<Product> cart = new ArrayList<>();

            while(true){
                System.out.println("ADICIONAR PRODUTO");
                String response = scanner.nextLine();
                if(response.equalsIgnoreCase("n")){
                    System.out.println("ENCERRAR APLICAÇÃO?");
                    String nao = scanner.nextLine();
                    if(nao.equalsIgnoreCase("s")){
                        System.out.println("ENCERRADO");
                        break;
                    }
                } else if(response.equalsIgnoreCase("s")){
                    System.out.println("NOME PRODUTO");
                    String nome = scanner.nextLine();

                    Product product = findProductName(products, nome);
                    if(product != null){
                        cart.add(product);
                        System.out.println("PRODUTO ADICIONADO AO CARRINHO");
                    } else {
                        System.out.println("NÃO ENCONTRADO");
                    }
                } else if(response.equalsIgnoreCase("r")){
                    System.out.println("PRODUTO REMOVIDO");
                    String remove = scanner.nextLine();
                    Product product = findProductName(products, remove);
                    if(product!=null){
                        cart.remove(product);
                        System.out.println("REMOVIDO");
                    } else {
                        System.out.println("NÃO ENCONTRADO");
                    }
                }
            }
            //verificar se o carrinho está vazio ou nao
            if(!cart.isEmpty()){
                double totalCart = valueAllCart(cart);
                System.out.println("\nPRODUTOS NO CARRINHO");
                showProduct(products);
                System.out.println("VALOR R$" + totalCart);
                System.out.println("CONCLUIR COMPRA?");
                String anwser = scanner.nextLine();
                if(anwser.equalsIgnoreCase("s")){
                    for(Product product: cart){
                        updateProduct(collection, product.getId());
                    }
                    System.out.println("COMPRA CONCLUÍDA");
                } else {
                    System.out.println("COMPRA NÃO CONCLUÍDA");
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }    
}