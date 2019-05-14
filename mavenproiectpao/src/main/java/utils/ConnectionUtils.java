package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private ConnectionUtils(){}
    private ConnectionUtils INSTANCE = null;

    public synchronized ConnectionUtils getInstance  (){
        if (INSTANCE == null )
            INSTANCE = new ConnectionUtils();
        return INSTANCE;
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/store?serverTimezone=UTC", "root", "george");
            if (conn == null)
                System.out.println("Connection not established");
            else System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
