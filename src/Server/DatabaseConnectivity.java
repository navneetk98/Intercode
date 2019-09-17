package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivity {

    static Connection connection;

    public static void connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Intercode";
            connection = DriverManager.getConnection(url, "root", "root");
            System.out.println("Connected to database");

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
