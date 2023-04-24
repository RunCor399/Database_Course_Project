package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import it.unibo.bd.model.Tax;

public class TaxTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public TaxTable() {
        this.dataSource = new DBConnection();
        this.tableName = "tassa";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public void insertNewTax(Tax tax) {
        String insertQuery = "INSERT INTO tassa(codCompagnia, dataFatturazione, dataPagamento, importo) "
                + " VALUES (?, ?, NULL, (SELECT COUNT(V.codVolo) AS numero_voli "
                + " FROM compagnia_aerea CA, volo V, calendario C " + " WHERE CA.codCompagnia = V.codCompagnia "
                + " AND C.codCompagnia = V.codCompagnia " + " AND C.codVolo = V.codVolo " + " AND CA.codCompagnia = ? "
                + " AND C.dataPartenza BETWEEN ? AND ?) * 5000)";

        PreparedStatement statement = this.buildQuery(insertQuery);
        try {
            statement.setString(1, tax.getAirlineCode());
            statement.setDate(2, new java.sql.Date(tax.getBillingDate().getTime()));
            statement.setString(3, tax.getAirlineCode());

            Calendar startDate = Calendar.getInstance();
            startDate.setTime(tax.getBillingDate());
            startDate.add(Calendar.MONTH, -1);
            statement.setDate(4, new java.sql.Date(startDate.getTime().getTime()));
            statement.setDate(5, new java.sql.Date(tax.getBillingDate().getTime()));

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet showAirlineTaxes(String airlineCode) {
        String query = "SELECT CA.codCompagnia, CA.nome, T.dataFatturazione, T.dataPagamento,  T.importo "
                + " FROM tassa T, compagnia_aerea CA " + " WHERE CA.codCompagnia = T.codCompagnia "
                + " AND T.codCompagnia = ?";

        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, airlineCode);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet showTotalIncome() {
        String query = "SELECT IFNULL(SUM(importo), 0) AS fatturato_totale " + "FROM tassa "
                + "WHERE dataPagamento IS NOT NULL";

        PreparedStatement statement = this.buildQuery(query);
        try {
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet showAirlinePaidAmountByYear(String airlineCode, int year) {
        String query = "SELECT IFNULL(SUM(importo), 0) importo " +
                       " FROM tassa  WHERE codCompagnia = ? AND YEAR(dataFatturazione) = ? " +
                       " AND dataPagamento IS NOT NULL";

        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, airlineCode);
            statement.setInt(2, year);
            ResultSet result = statement.executeQuery();
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void payAirlineTax(String airlineCode, Date billingDate, Date paymentDate) {
        if (!this.findByPrimaryKey(airlineCode, billingDate)) {
        } else {
            String query = "UPDATE tassa " + " SET dataPagamento = ? " + " WHERE codCompagnia = ? "
                    + " AND dataFatturazione = ? ";
            PreparedStatement statement = this.buildQuery(query);
            try {
                statement.setDate(1, new java.sql.Date(paymentDate.getTime()));
                statement.setString(2, airlineCode);
                statement.setDate(3, new java.sql.Date(billingDate.getTime()));

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findByPrimaryKey(String code, Date billingDate) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codCompagnia = ? " + " AND dataFatturazione = ?";

        PreparedStatement statement = this.buildQuery(query);
        try {
            statement.setString(1, code);
            statement.setDate(2, new java.sql.Date(billingDate.getTime()));
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
