package it.unibo.bd.model;

public class FlightService {
    private String flightServiceId;
    private String flightAirlineCode;
    private String flightCode;
    private String airlineCode;
    private String employeeCode;
    
    public FlightService(String flightServiceId, String flightAirlineCode, String flightCode, String airlineCode, String employeeCode) {
        this.flightServiceId = flightServiceId;
        this.flightAirlineCode = flightAirlineCode;
        this.flightCode = flightCode;
        this.airlineCode = airlineCode;
        this.employeeCode = employeeCode;
    }

    public String getId() {
        return flightServiceId;
    }

    public void setId(String flightServiceId) {
        this.flightServiceId = flightServiceId;
    }

    public String getFlightAirlineCode() {
        return flightAirlineCode;
    }

    public void setFlightAirlineCode(String flightAirlineCode) {
        this.flightAirlineCode = flightAirlineCode;
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

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    
    
}
