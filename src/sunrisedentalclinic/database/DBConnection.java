
package sunrisedentalclinic.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunrisedentalclinic_DB?useSSL=false", "root", "");
            System.out.println("Database Connected Successfully!");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
}
