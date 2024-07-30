package org.practice.hw3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static String dbURL = "jdbc:postgresql://localhost:5432/aston";
    private static String dbUser = "postgres";
    private static String dbPass = "1234";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
