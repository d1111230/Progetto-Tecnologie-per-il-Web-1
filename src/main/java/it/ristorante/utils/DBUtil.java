package it.ristorante.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/ristorante_db?serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; // Metti la tua password di MySQL

    public static Connection getConnection() throws SQLException {
        try {
            // Carico il driver dentro la cartella lib
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Errore: Driver MySQL non trovato!");
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}