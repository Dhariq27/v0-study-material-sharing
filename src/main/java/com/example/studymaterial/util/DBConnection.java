package com.example.studymaterial.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class demonstrating plain JDBC connection to Oracle database.
 * This is provided as an example; Spring Boot datasource is preferred for production.
 * 
 * Example usage:
 * Connection conn = DBConnection.getConnection();
 * // Use connection
 * conn.close();
 */
public class DBConnection {
    
    // CHANGE_ME: Update with your Oracle database credentials
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static final String DB_USER = "study_user";
    private static final String DB_PASSWORD = "study_password";
    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    
    /**
     * Get a JDBC connection to Oracle database
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found: " + e.getMessage());
            throw new SQLException("Driver not found", e);
        }
        
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    /**
     * Test the connection
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Connection successful!");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }
}
