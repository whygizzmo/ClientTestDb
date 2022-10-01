package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:/Users/дима/Desktop/db/java_test_5/test.db");
    }
}
