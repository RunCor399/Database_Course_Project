package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String dbName = "aeroporto_gestionale";
    
    public DBConnection() {}
    
    public Connection getMySQLConnection()  {
       String driver = "com.mysql.jdbc.Driver";
       //String dbUri = "jdbc:mysql://localhost:3306/"+dbName;
       String dbUri = "jdbc:mysql://localhost:3306/" + dbName + "?characterEncoding=latin1";
           String userName = "root";
           String password = "gestionaleAeroporto";
        
       Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUri, userName, password);
        }
        catch (ClassNotFoundException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }
        catch(SQLException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }
        return connection;
    }
}
