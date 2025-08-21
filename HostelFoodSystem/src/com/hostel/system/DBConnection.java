package com.hostel.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/HostelFoodSystem";
    private static final String USER = "app_user";
    private static final String PASSWORD = "secure_password";

    // Method to get a connection
    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the JDBC driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Add the driver to the project.");
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found.");
        }
        // Return the database connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
