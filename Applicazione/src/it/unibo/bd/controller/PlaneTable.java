package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.Plane;

public class PlaneTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public PlaneTable() {
        this.dataSource = new DBConnection();
        this.tableName = "aereo";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public ResultSet showTotalPlaneFlights(String planeCode) {
        String query = "SELECT COUNT(codVolo) AS totale_voli " + " FROM aereo A, volo V "
        + " WHERE A.codAereo = V.codAereo " + " AND V.codAereo = ?";
        PreparedStatement statement = this.buildQuery(query);

        try {
            statement.setString(1, planeCode);
            ResultSet result = statement.executeQuery();
            
            return result;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

    public void insertNewPlane(Plane plane) {
        if (this.findByPrimaryKey(plane.getPlaneCode())) {
        } else {
            String query = "INSERT INTO aereo(codAereo, modelloAeromobile, numeroPosti, flagManutenzione, codCompagnia) " + 
                           " VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement statement = this.buildQuery(query);
            try {
                statement.setString(1, plane.getPlaneCode());
                statement.setString(2, plane.getPlaneModel());
                statement.setInt(3, plane.getSeatsNumber());
                statement.setBoolean(4, plane.getMaintenanceFlag());
                statement.setString(5, plane.getAirlineCode());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int updatePlaneMaintenanceFlag(String planeCode) {
        boolean flag;
        String query1 = "SELECT flagManutenzione FROM "+ this.tableName + " WHERE codAereo = ?";
        String query2 = "UPDATE aereo SET flagManutenzione = ? WHERE codAereo = ?";
                
        PreparedStatement statement1 = this.buildQuery(query1);
        PreparedStatement statement2 = this.buildQuery(query2);
        try {
            statement1.setString(1, planeCode);
            ResultSet result = statement1.executeQuery();
            if(result.next()) {
                flag = result.getBoolean("flagManutenzione");
                
                statement2.setBoolean(1, !flag);
                statement2.setString(2, planeCode);
                
                statement2.executeUpdate();
                
                if(!flag) {
                    return 0;
                } else {
                    return 1;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2;
    }

    public boolean findByPrimaryKey(String code) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codAereo = ?";
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
