package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.AirlineEmployee;
import it.unibo.bd.model.AirportEmployee;

public class AirportEmployeeTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public AirportEmployeeTable() {
        this.dataSource = new DBConnection();
        this.tableName = "dipendente_aeroporto";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public boolean insertNewAirportEmployee(AirportEmployee employee) {
        String insertQuery = "INSERT INTO dipendente_aeroporto(codDipendente, nome, cognome, genere, citta, indirizzo, ruolo, ruoloInterno) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = this.buildQuery(insertQuery);
        try {
            statement.setString(1, employee.getCode());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getSurname());
            if (employee.getGender() == AirlineEmployee.Gender.M) {
                statement.setString(4, "M");
            } else {
                statement.setString(4, "F");
            }
            statement.setString(5, employee.getCity());
            statement.setString(6, employee.getAdress());
            statement.setString(7, employee.getRole());
            if (employee.getSpecificRole().isPresent()) {
                statement.setString(8, employee.getSpecificRole().get());
            } else {
                statement.setString(8, null);
            }
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ResultSet getEmployeesByRole(String role) {
        String query = "SELECT * FROM dipendente_aeroporto WHERE ruolo = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, role);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean findByPrimaryKey(String code) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codDipendente = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, code);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return true;
                // SAVE IN A OBJECT
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
