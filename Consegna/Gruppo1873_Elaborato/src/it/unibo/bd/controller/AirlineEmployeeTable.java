package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.AirlineEmployee;

public class AirlineEmployeeTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public AirlineEmployeeTable() {
        this.dataSource = new DBConnection();
        this.tableName = "dipendente_compagnia";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public void insertNewAirlineEmployee(AirlineEmployee employee) {
        String query = "INSERT INTO dipendente_compagnia(codDipendente, codCompagnia, nome, cognome, genere, citta, indirizzo, ruolo, grado) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, employee.getCode());
            statement.setString(2, employee.getAirlineCode());
            statement.setString(3, employee.getName());
            statement.setString(4, employee.getSurname());
            if (employee.getGender() == AirlineEmployee.Gender.M) {
                statement.setString(5, "M");
            } else {
                statement.setString(5, "F");
            }
            statement.setString(6, employee.getCity());
            statement.setString(7, employee.getAdress());
            statement.setString(8, employee.getRole());
            if (employee.getRank().isPresent()) {
                statement.setString(9, employee.getRank().get());
            } else {
                statement.setString(9, null);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getAirlineEmployeesByRole(String airlineCode, String role) {
        String query = "SELECT codDipendente, nome, cognome, genere, citta, indirizzo, ruolo, grado "
                + " FROM dipendente_compagnia " + " WHERE ruolo = ? " + " AND codCompagnia = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, role);
            statement.setString(2, airlineCode);
            ResultSet result = statement.executeQuery();
            return result;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean findByPrimaryKey(String employeeCode, String airlineCode) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codDipendente = ? " + " AND codCompagnia = ?";
        PreparedStatement statement = this.buildQuery(query);

        try {
            statement.setString(1, employeeCode);
            statement.setString(2, airlineCode);
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
