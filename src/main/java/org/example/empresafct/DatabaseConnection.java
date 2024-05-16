package org.example.empresafct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/EmpresaFCT";
    private static final String DATABASE_USER = "EmpresaFCT";
    private static final String DATABASE_PASSWORD = "1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error Connecting to Database: " + e.getMessage());
        }
        return connection;
    }
}