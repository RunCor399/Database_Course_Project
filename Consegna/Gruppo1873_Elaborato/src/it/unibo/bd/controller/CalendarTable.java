package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalendarTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;
    
    public CalendarTable() {
        this.dataSource =  new DBConnection();
        this.tableName = "calendario";
        this.connection = this.dataSource.getMySQLConnection();
    }
    
    public boolean findByPrimaryKey(String code) {
        String query = "SELECT * FROM " + this.tableName + " WHERE idCalendario = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, code);
            ResultSet result = statement.executeQuery(); 
            
            if(result.next()) {
                return true;
            }
            else {
                return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
             
    }
    
    public PreparedStatement buildQuery(String query) {
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}
