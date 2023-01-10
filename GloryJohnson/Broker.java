package GloryJohnson;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Broker {

    public static void main(String[] args) {
        // instantiate the consumer class
        Consumer consumer = new Consumer();
        // instantiate the product class
        Products products = new Products();
        // instantiate the broker class
        // Broker broker = new Broker();
        // instantiate the retailer class
        Retailer retailer = new Retailer();
        // create an instance of random
        Random rand = new Random();
        // get list of consumers
        List<List<String>> consumerDetails = consumer.consumerList();
        // check if the list of consumer is empty
        if (consumerDetails != null) {
            // Scanner scan = new Scanner(System.in);
            // pick a consumer at random between id 1 to 20
            consumer.setConsumer(rand.nextInt(20) + "", consumerDetails);
            // welcome the consumer back
            consumer.welcomeBack();
            // get a list of products
            List<List<String>> productList = products.showProducts();
            // select a product at random that the consumer will order
            products.setSelectedProduct(rand.nextInt(71) + "", productList);
            System.out.println("Product Name: " + products.name);
            products.quality = rand.nextInt(10);
            // init the price range
            int minPrice = 0, maxPrice = 0;
            // assign values to the range at random
            while (true) {
                minPrice = rand.nextInt(20);
                maxPrice = rand.nextInt(120);
                if (maxPrice > minPrice)
                    break;
            }
            // set the price range by casting the interger to float
            products.minPrice = (float) minPrice;
            products.maxPrice = (float) maxPrice;
            // set the delivery constrain to fetch at random
            products.delivery = rand.nextBoolean();
            System.out.println("Product Quantity: " + products.quality);
            System.out
                    .println("Product Price Range: [Min: " + products.minPrice + " | Max: " + products.maxPrice + "]");
            // get the list of retialers for the selected products
            // List<List<String>> getRetailers = retailer.getRetailers(products.name);
            List<List<String>> getRetailers = retailer.getRetailers("Crackers - Trio");
            // show the total number of retailers.
            System.out.println("Total Retailers: " + getRetailers.size());
            // request an offer from the retailer
            int[] price = new int[] { minPrice, maxPrice };
            int trxOffer = rand.nextInt(536871066);
            List<List<String>> offersList = retailer.requestOffer(getRetailers, price, products.delivery,
                    products.quality, trxOffer);
            if (offersList.size() > 0) {
                List<List<String>> offerResponse = offerResponse(trxOffer);
                consumer.offerResponse(offerResponse, trxOffer);
            } else {
                System.out.println("Sorry no offer from Retailer at the moment please try again");
            }
        }

    }

    private static List<List<String>> offerResponse(int trxId) {
        Database db = new Database();
        List<List<String>> lists = new LinkedList<>();
        try {
            String sql = String.format(
                    "SELECT * FROM trxOffer WHERE trxid LIKE '%s' ORDER BY price,quantity ASC",
                    trxId);
            ResultSet rs = db.select(sql);
            while (rs.next()) {
                String _rid = rs.getString("rid");
                String _pid = rs.getString("pid");
                String _name = rs.getString("name");
                String _delivery = rs.getString("delivery");
                String _quantity = rs.getString("quantity");
                String _price = rs.getString("price");
                String str = _rid + "," + _pid + "," + _name + "," + _delivery + "," + _quantity + "," + _price;

                lists.add(Arrays.asList(str.split(",")));
            }
            return lists;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lists;
    }
}
