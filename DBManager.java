package com.example.demo.pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    final String DB_URL = "jdbc:mysql://";
    final String DB_IP = "localhost";
    final String DB_PORT = "3306";
    final String DB_NAME = "mydb";
    final String DB_USER = "root";
    final String DB_PASS = "0895064046";
    final String URL = DB_URL + DB_IP+":" + DB_PORT + "/"+ DB_NAME;

    private static DBManager ourInstance = new DBManager();

    private Connection connection;

    public static DBManager getInstance() {
        return ourInstance;
    }

    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver successfully integrated");
            this.connection = DriverManager.getConnection(URL,DB_USER,DB_PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Maybe you are missing a jar?");
        } catch (SQLException e) {
            System.out.println("Error connecting to DB" + e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

}
