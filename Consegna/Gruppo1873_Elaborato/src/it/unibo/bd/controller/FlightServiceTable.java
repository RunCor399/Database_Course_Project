package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.FlightService;

public class FlightServiceTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public FlightServiceTable() {
        this.dataSource = new DBConnection();
        this.tableName = "servizio_volo";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public void addFlightService(FlightService service) {
        if (this.findByPrimaryKey(service.getId())) {
        } else {
            String query = "INSERT INTO servizio_volo(idServizioVolo, volo_codCompagnia, volo_codVolo, codDipendente, codCompagnia) " + 
                           " VALUES(?, ?, ?, ?, ?)";
            
            PreparedStatement statement = this.buildQuery(query);
            try {
                statement.setString(1, service.getId());
                statement.setString(2, service.getFlightAirlineCode());
                statement.setString(3, service.getFlightCode());
                statement.setString(4, service.getEmployeeCode());
                statement.setString(5, service.getAirlineCode());
                
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findByPrimaryKey(String idServizio) {
        String query = "SELECT * FROM " + this.tableName + " WHERE idServizioVolo = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, idServizio);
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
