package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = System.getenv("SQLAZURECONNSTR_pavsuprdb");
    
    public static Connection getConnection() throws SQLException {
        System.out.println("Attempting to connect with URL: " + JDBC_URL);
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("JDBC driver loaded successfully");
            
            Connection conn = DriverManager.getConnection(JDBC_URL);
            System.out.println("Connection established successfully");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
            throw new SQLException("Unable to load JDBC driver", e);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
