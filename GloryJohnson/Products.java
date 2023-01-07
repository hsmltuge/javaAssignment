package GloryJohnson;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Products {
    String id, retailer, name;
    int quality;
    Float minPrice, maxPrice;
    boolean delivery;
    Database db = new Database();

    public List<List<String>> showProducts() {
        List<List<String>> lists = new LinkedList<>();
        try {
            ResultSet rs = this.db.select("SELECT DISTINCT NAME FROM products");
            int i = 1;
            // System.out.println("Select the product SN you wish to order");
            while (rs.next()) {
                String _name = rs.getString("name");
                String str = i + "," + _name;
                // System.out.println("Product SN: " + i + " Name: " + _name);
                lists.add(Arrays.asList(str.split(",")));
                i++;
            }
            return lists;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public void setSelectedProduct(String id, List<List<String>> list) {
        try {
            for (List<String> lst : list) {
                if (lst.toArray()[0].toString().trim().equals(id.toString().trim())) {
                    this.name = lst.toArray()[1].toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
