package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = System.getenv("SQLAZURECONNSTR_pavsuprdb");
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load JDBC driver", e);
        }
        return DriverManager.getConnection(JDBC_URL);
    }
}
