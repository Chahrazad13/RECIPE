package com.example.tpjavarecipes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/user_receipt_db?useSSL=false";

    private static final String MYSQL_USER = "root";

    private static final String MYSQL_PASS = "Ikramkarami0+";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, MYSQL_USER, MYSQL_PASS);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection to database error"+e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
