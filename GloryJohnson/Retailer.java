package GloryJohnson;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Retailer {

    Database db = new Database();

    private Boolean shouldOffer() {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    public List<List<String>> getRetailers(String name) {
        List<List<String>> lists = new LinkedList<>();
        try {
            String sql = String.format(
                    "SELECT r.id rid,p.id pid,r.name name,p.price, p.delivery,p.quantity FROM retailer r JOIN products p ON r.id LIKE p.retailer WHERE p.name LIKE '%s'",
                    name);
            ResultSet rs = this.db.select(sql);
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

    public List<List<String>> requestOffer(List<List<String>> listOfRetailers, int[] price, boolean delivery,
            int quality) {
        List<List<String>> lists = new LinkedList<>();
        try {
            for (List<String> lst : listOfRetailers) {
                if (!shouldOffer())
                    continue;
                String _rid = (String) lst.toArray()[0];
                String _pid = (String) lst.toArray()[1];
                String _name = (String) lst.toArray()[2];
                String _delivery = (String) lst.toArray()[3];
                String _quantity = (String) lst.toArray()[4];
                String _price = (String) lst.toArray()[5];
                Boolean deliv = false;
                if (_delivery.equalsIgnoreCase("yes"))
                    deliv = true;
                if (deliv != delivery)
                    continue;
                if (Integer.parseInt(_price) < price[0] || Integer.parseInt(_price) > price[1])
                    continue;
                if (Integer.parseInt(_quantity) < quality)
                    continue;
                System.out.println(deliv + " | " + delivery + " - " + _delivery);
                System.out.println(_name + " " + _delivery + " " + delivery + " [" + _price + "=" + price[0] + " - "
                        + price[1] + "] " + _quantity + " " + quality);
            }
            return lists;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lists;
    }
}
