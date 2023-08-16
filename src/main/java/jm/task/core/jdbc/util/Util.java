package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URLFIXED = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "Kruto1100";
    private static Connection connection;
    public static Connection getConn() {
        try {
            connection = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
