package GloryJohnson;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Consumer {

    public String name, address, dob, age, gender, id;
    Database db = new Database();

    public void welcomeBack() {
        System.out.println("Welcome back " + this.name);
    }

    public List<List<String>> consumerList() {

        List<List<String>> lists = new LinkedList<>();
        try {

            ResultSet rs = this.db.select("SELECT * FROM consumer");
            while (rs.next()) {
                int _id = rs.getInt("id");
                String _name = rs.getString("name");
                String _address = rs.getString("address");
                String _dob = rs.getString("dob");
                String _gender = rs.getString("gender");
                String str = _id + "," + _name + "," + _address + "," + _dob + "," + _gender;
                // System.out.println("Consumer SN: " + _id + " Name: " + _name);
                lists.add(Arrays.asList(str.split(",")));
            }
            return lists;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void setConsumer(String id, List<List<String>> list) {
        try {
            for (List<String> lst : list) {
                if (lst.toArray()[0].toString().trim().equals(id.toString().trim())) {
                    this.id = id;
                    this.name = lst.toArray()[1].toString();
                    this.address = lst.toArray()[2].toString();
                    this.dob = lst.toArray()[3].toString();
                    this.gender = lst.toArray()[4].toString();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void offerResponse(List<List<String>> offers, int trxId) {
        try {
            Random rand = new Random();
            Boolean acceptOffer = rand.nextBoolean();
            if (acceptOffer) {
                int accepts = rand.nextInt(offers.size());
                int i = 1;
                for (List<String> list : offers) {
                    if (i == accepts) {
                        String _name = (String) list.toArray()[2];
                        System.out.println("\n.................................................");
                        System.out.println("accept offer from".toUpperCase() + " " + _name.toUpperCase());
                        System.out.println(".................................................\n");
                    }
                }
                Database db = new Database();
                db.delete(trxId);
            } else {
                System.out.println("Sorry consumer rejected the offer");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
