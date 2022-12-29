package GloryJohnson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection c = null;

    public Database() {
        try {
            if (this.c == null) {
                Class.forName("org.sqlite.JDBC");
                this.c = DriverManager.getConnection("jdbc:sqlite:test.db");
                this.c.setAutoCommit(false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet select(String sql) {
        try {
            Statement stmt = this.c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Boolean query(String sql) {
        try {
            Statement stmt = this.c.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
