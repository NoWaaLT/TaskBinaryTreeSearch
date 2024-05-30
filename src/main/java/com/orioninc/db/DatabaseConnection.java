package com.orioninc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:h2:file:~/IdeaProjects/TaskBinaryTreeSearch/src/main/resources";
    private static final String USER_DB = "sa";
    private static final String USER_PASS = "";

    public Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_DB, USER_PASS);
    }
}
