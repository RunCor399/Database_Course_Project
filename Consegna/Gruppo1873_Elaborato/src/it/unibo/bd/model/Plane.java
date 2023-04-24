package it.unibo.bd.model;

public class Plane {
    private String planeCode;
    private String airlineCode;
    private String planeModel;
    private int seatsNumber;
    private boolean maintenanceFlag;
    
    public Plane(String planeCode, String airlineCode, String planeModel, int seatsNumber, boolean maintenanceFlag) {
        this.planeCode = planeCode;
        this.airlineCode = airlineCode;
        this.planeModel = planeModel;
        this.seatsNumber = seatsNumber;
        this.maintenanceFlag = maintenanceFlag;
    }
    
    public String getPlaneCode() {
        return planeCode;
    }
    
    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }
    
    public String getAirlineCode() {
        return airlineCode;
    }
    
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
    
    public String getPlaneModel() {
        return planeModel;
    }
    
    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
    }
    
    public int getSeatsNumber() {
        return seatsNumber;
    }
    
    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
    
    public boolean getMaintenanceFlag() {
        return maintenanceFlag;
    }
    
    public void setMaintenanceFlag(boolean maintenanceFlag) {
        this.maintenanceFlag = maintenanceFlag;
    }   
}
