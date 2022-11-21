package org.gift.backoffice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCProcessor {
    private static final String URL = "jdbc:postgresql://localhost:5432/gift_db";
    private static final String USER = "postgres";
    private static final String PASS = "V2679v2679";

    private Connection connection;
    //private static List<Connection> connectionPool;

    public JDBCProcessor() {
        //TODO Set default settings => change later
        //TODO and organize connection pool
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.setAutoCommit(false);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException exception) {
            //logger
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
