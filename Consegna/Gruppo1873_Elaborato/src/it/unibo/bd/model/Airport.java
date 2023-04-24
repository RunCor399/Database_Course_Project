package it.unibo.bd.model;

public class Airport {
    private String ICAO;
    private String IATA;
    private String city;
    
    public Airport(String ICAO, String IATA, String city) {
        this.ICAO = ICAO;
        this.IATA = IATA;
        this.city = city;
    }

    public String getICAO() {
        return ICAO;
    }

    public void setICAO(String iCAO) {
        ICAO = iCAO;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String iATA) {
        IATA = iATA;
    }

    public String getCitta() {
        return city;
    }

    public void setCitta(String city) {
        this.city = city;
    }
    
    
}
