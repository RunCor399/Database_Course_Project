package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.Maintenance;

public class MaintenanceTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public MaintenanceTable() {
        this.dataSource = new DBConnection();
        this.tableName = "manutenzione";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public void maintenancePlane(Maintenance maintenance) {
        String query = "INSERT INTO manutenzione(idManutenzione, dataInizio, dataFine, codAereo, codDipendente) "
                + " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, maintenance.getId());
            statement.setDate(2, new java.sql.Date(maintenance.getStartDate().getTime()));

            if (maintenance.getFinishDate().isPresent()) {
                statement.setDate(3, new java.sql.Date(maintenance.getFinishDate().get().getTime()));
            } else {
                statement.setDate(3, null);
            }

            statement.setString(4, maintenance.getPlaneCode());
            statement.setString(5, maintenance.getEmployeeCode());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet showMaintenancePlanes() {
        String query = "SELECT M.idManutenzione, CA.codCompagnia, A.codAereo, M.codDipendente, M.dataInizio, M.dataFine"
                + " FROM compagnia_aerea CA, aereo A, dipendente_aeroporto DA, manutenzione M "
                + " WHERE M.codAereo = A.codAereo " + " AND M.codDipendente = DA.codDipendente "
                + " AND A.codCompagnia = CA.codCompagnia";

        PreparedStatement statement = this.buildQuery(query);
        try {
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean findByPrimaryKey(String code) {
        String query = "SELECT * FROM " + this.tableName + " WHERE idManutenzione = ?";
        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, code);
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
