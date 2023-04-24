package it.unibo.bd.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unibo.bd.model.Airline;
import it.unibo.bd.model.Calendar;
import it.unibo.bd.model.Flight;

public class AirlineTable {
    private DBConnection dataSource;
    private String tableName;
    private Connection connection;

    public AirlineTable() {
        this.dataSource = new DBConnection();
        this.tableName = "compagnia_aerea";
        this.connection = this.dataSource.getMySQLConnection();
    }

    public void insertNewAirline(Airline airline) {
        String insertQuery = "INSERT INTO compagnia_aerea(codCompagnia, nome, sede) " + " VALUES (?, ?, ?)";
        PreparedStatement statement = this.buildQuery(insertQuery);
        try {
            statement.setString(1, airline.getAirlineCode());
            statement.setString(2, airline.getAirlineName());
            statement.setString(3, airline.getHub());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet showAirlineDestinations(String airlineCode) {
        String query = "SELECT DISTINCT V.destinazioneICAO, A.IATA, A.citta " + " FROM compagnia_aerea CA, volo V, aeroporto A "
                + " WHERE CA.codCompagnia = ? " + " AND CA.codCompagnia = V.codCompagnia "
                + " AND V.destinazioneICAO = A.ICAO ";

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


    public void insertNewFlight(Flight flight, Calendar calendar) {
        String query1 = "INSERT INTO volo(codCompagnia, codVolo, origineICAO, destinazioneICAO, codAereo)"
                + " VALUES (?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO calendario(idCalendario, codCompagnia, codVolo, dataPartenza, oraPartenza, dataArrivo, oraArrivo) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement1 = this.buildQuery(query1);
        PreparedStatement statement2 = this.buildQuery(query2);
        try {
            statement1.setString(1, flight.getAirlineCode());
            statement1.setString(2, flight.getFlightCode());
            statement1.setString(3, flight.getDepartureICAO());
            statement1.setString(4, flight.getArrivalICAO());
            statement1.setString(5, flight.getPlaneCode());

            statement2.setString(1, calendar.getCalendarId());
            statement2.setString(2, calendar.getAirlineCode());
            statement2.setString(3, calendar.getFlightCode());
            statement2.setDate(4, new java.sql.Date(calendar.getDepartureDate().getTime()));
            statement2.setTime(5, new java.sql.Time(calendar.getDepartureDate().getTime()));
            if (calendar.getArrivalDate().isPresent()) {
                statement2.setDate(6, new java.sql.Date(calendar.getArrivalDate().get().getTime()));
                statement2.setTime(7, new java.sql.Time(calendar.getArrivalDate().get().getTime()));
            } else {
                statement2.setDate(6, null);
                statement2.setTimestamp(7, null);
            }

            statement1.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet showFlightInfo(String airlineCode) {
        String query = "SELECT V.codVolo, V.codCompagnia, V.origineICAO, V.destinazioneICAO, DC.codDipendente, DC.nome, DC.cognome, DC.ruolo, DC.grado "
                + " FROM volo V, servizio_volo SV, dipendente_compagnia DC " + " WHERE V.codVolo = SV.volo_codVolo "
                + " AND V.codCompagnia = SV.volo_codCompagnia " + " AND SV.codCompagnia = DC.codCompagnia "
                + " AND SV.codDipendente = DC.codDipendente " + " AND V.codCompagnia = ?";


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

    public boolean findByPrimaryKey(String code) {
        String query = "SELECT * FROM " + this.tableName + " WHERE codCompagnia = ?";
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