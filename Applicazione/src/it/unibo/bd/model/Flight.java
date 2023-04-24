package it.unibo.bd.model;

public class Flight {
    private String flightCode;
    private String airlineCode;
    private String planeCode;
    private String departureICAO;
    private String arrivalICAO;
    
    public Flight(String flightCode, String airlineCode, String planeCode, String departureICAO, String arrivalICAO) {
        this.flightCode = flightCode;
        this.airlineCode = airlineCode;
        this.planeCode = planeCode;
        this.departureICAO = departureICAO;
        this.arrivalICAO = arrivalICAO;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public String getDepartureICAO() {
        return departureICAO;
    }

    public void setDepartureICAO(String departureICAO) {
        this.departureICAO = departureICAO;
    }

    public String getArrivalICAO() {
        return arrivalICAO;
    }

    public void setArrivalICAO(String arrivalICAO) {
        this.arrivalICAO = arrivalICAO;
    }
    
    
}
