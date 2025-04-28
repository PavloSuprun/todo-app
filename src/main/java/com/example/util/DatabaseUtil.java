package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = 
    "jdbc:sqlserver://pavsupr-sql-server-failover-group.database.windows.net:1433;" +
    "database=pavsuprdb;" +
    "user=pavsupr@pavsupr-sql-server;" +
    "password=password;" +
    "encrypt=true;" +
    "trustServerCertificate=false;" +
    "hostNameInCertificate=*.database.windows.net;" +
    "loginTimeout=30;";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load JDBC driver", e);
        }
        return DriverManager.getConnection(JDBC_URL);
    }
}
