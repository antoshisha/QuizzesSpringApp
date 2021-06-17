package ru.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class ConnectionDB {
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("---ERROR WHILE LOAD DB DRIVER---");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/quizzes", "postgres", "postgres");
            System.out.println("---CONNECTION IS SUCCESSFUL---");
        } catch (SQLException throwables) {
            System.out.println("---CANT GET CONNECTION---");
            throwables.printStackTrace();
        } return connection;
    }
}
