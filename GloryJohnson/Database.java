package GloryJohnson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection c = null;

    public Database() {
        try {
            if (this.c == null) {
                Class.forName("org.sqlite.JDBC");
                this.c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
            this.c.setAutoCommit(false);
            Statement stmt = this.c.createStatement();
            stmt.executeUpdate(sql);
            this.c.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public Boolean delete(int trxId) {
        try {
            this.c.setAutoCommit(false);
            Statement stmt = this.c.createStatement();
            String sql = String.format("DELETE FROM trxOffer WHERE trxid LIKE '%s'", trxId);
            stmt.executeUpdate(sql);
            this.c.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
