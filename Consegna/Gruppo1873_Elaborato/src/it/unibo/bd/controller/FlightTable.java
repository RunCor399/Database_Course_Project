package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;
    
    public FlightTable() {
        this.dataSource =  new DBConnection();
        this.tableName = "volo";
        this.connection = this.dataSource.getMySQLConnection();
    }
    
    public boolean findByPrimaryKey(String flightCode, String airlineCode) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codCompagnia = ? " + " AND codVolo = ?";
        PreparedStatement statement = this.buildQuery(query);

        try {
            statement.setString(1, airlineCode);
            statement.setString(2, flightCode);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return true;
            } else {
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
