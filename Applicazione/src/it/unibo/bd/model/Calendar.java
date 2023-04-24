package it.unibo.bd.model;

import java.util.Date;
import java.util.Optional;

public class Calendar {
    private String calendarId;
    private String airlineCode;
    private String flightCode;
    private Date departureDate;
    private Optional<Date> arrivalDate;
    
    public Calendar(String calendarId, String airlineCode, String flightCode, Date departureDate, Optional<Date> arrivalDate) {
        this.calendarId = calendarId;
        this.airlineCode = airlineCode;
        this.flightCode = flightCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String planeCode) {
        this.flightCode = planeCode;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String id) {
        this.calendarId = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Optional<Date> getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Optional<Date> arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
