package it.unibo.bd.model;

public class Airline {
    private String airlineCode;
    private String airlineName;
    private String hub;
    
    public Airline(String airlineCode, String airlineName, String hub) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.hub = hub;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }
}
