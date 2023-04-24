package it.unibo.bd.model;

import java.util.Date;
import java.util.Optional;

public class Maintenance {
    private String id;
    private String employeeCode;
    private String planeCode;
    private Date startDate;
    private Optional<Date> finishDate;
    
    public Maintenance(String id, String employeeCode, String planeCode, Date startDate, Optional<Date> finishDate) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.planeCode = planeCode;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPlaneCode() {
        return planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Optional<Date> getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Optional<Date> finishDate) {
        this.finishDate = finishDate;
    }
    
    
}
