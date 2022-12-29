package GloryJohnson;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Products products = new Products();
        Random rand = new Random();
        List<List<String>> consumerDetails = consumer.consumerList();
        if (consumerDetails != null) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Select Customer SN:");
            String consumerSelected = rand.nextInt(20) + "";
            consumer.setConsumer(consumerSelected, consumerDetails);
            consumer.welcomeBack();
            System.out.println("I am your broker agent");
            List<List<String>> productList = products.showProducts();
            System.out.println("Please select a product");
            String selectedProduct = rand.nextInt(71) + "";
            products.setSelectedProduct(selectedProduct, productList);
            System.out.println(products.name);

            scan.close();
        }

    }
}
