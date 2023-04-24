package it.unibo.bd.controller;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Optional;

import it.unibo.bd.model.Airline;
import it.unibo.bd.model.AirlineEmployee;
import it.unibo.bd.model.AirportEmployee;
import it.unibo.bd.model.Calendar;
import it.unibo.bd.model.Employee;
import it.unibo.bd.model.Flight;
import it.unibo.bd.model.FlightService;
import it.unibo.bd.model.Maintenance;
import it.unibo.bd.model.Plane;
import it.unibo.bd.model.Tax;

public class MainController {
    private final AirlineEmployeeTable airlineEmployeeTable = new AirlineEmployeeTable();
    private final AirportEmployeeTable airportEmployeeTable = new AirportEmployeeTable();
    private final TaxTable taxTable = new TaxTable();
    private final PlaneTable planeTable = new PlaneTable();
    private final MaintenanceTable maintenanceTable = new MaintenanceTable();
    private final AirlineTable airlineTable = new AirlineTable();
    private final FlightServiceTable flightServiceTable = new FlightServiceTable();
    private final CalendarTable calendarTable = new CalendarTable();
    private final AirportTable airportTable = new AirportTable();
    private final FlightTable flightTable = new FlightTable();
    
    public MainController() {}
    
    public boolean checkAirlineCode(String code) {
        return this.airlineTable.findByPrimaryKey(code);
    }
    
    public void insertAirline(String airlineCode, String name, String hub) {
        if(airlineTable.findByPrimaryKey(airlineCode)) {
        } else {
            Airline airline = new Airline(airlineCode, name, hub);            
            airlineTable.insertNewAirline(airline);
        }       
    }
    
    public void insertNewAirlineEmployee(String employeeCode, String airlineCode, String name, String surname, String genderID, String city, String adress, String role, Optional<String> rank) {
        if((airlineEmployeeTable.findByPrimaryKey(employeeCode, airlineCode)) || !(airlineTable.findByPrimaryKey(airlineCode))) {
        } else {
            AirlineEmployee airlineEmployee;
            Employee.Gender gender;
            if(genderID.contentEquals("M")) {
                gender = Employee.Gender.M;
            } else {
                gender = Employee.Gender.F;
            }
            
            if(rank.isEmpty()) {
                Optional<String> optRank = Optional.empty();
                airlineEmployee = new AirlineEmployee(employeeCode, airlineCode, name, surname, gender, city, adress, role, optRank);
            } else {
                airlineEmployee = new AirlineEmployee(employeeCode, airlineCode, name, surname, gender, city, adress, role, rank);
            }
        
            airlineEmployeeTable.insertNewAirlineEmployee(airlineEmployee);
        }
    }
    
    public void insertNewFlight(String airlineCode, String flightCode, String departureICAO, String destinationICAO, String planeCode, String calendarID, Date departureDate, Optional<Date> arrivalDate) {
        if((calendarTable.findByPrimaryKey(calendarID)) || !(airportTable.findByPrimaryKey(departureICAO)) || 
                !(airportTable.findByPrimaryKey(destinationICAO)) || (flightTable.findByPrimaryKey(flightCode, airlineCode)) || 
                !(airlineTable.findByPrimaryKey(airlineCode)) || !(planeTable.findByPrimaryKey(planeCode))){
                  
              } else {
                  Flight flight;
                  Calendar calendar;
                  
                  flight = new Flight(flightCode, airlineCode, planeCode, departureICAO, destinationICAO);
                  
                  if(arrivalDate.isEmpty()) {
                      Optional<Date> optArrival = Optional.empty();
                      calendar = new Calendar(calendarID, airlineCode, flightCode, departureDate, optArrival);
                  } else {
                      calendar = new Calendar(calendarID, airlineCode, flightCode, departureDate, arrivalDate);
                  }
                 
                      airlineTable.insertNewFlight(flight, calendar);
              }
    }
    
    public ResultSet showEmployeesByRole(String airlineCode, String role) {
        if(!airlineTable.findByPrimaryKey(airlineCode)) {
            return null;
        } else {
            return airlineEmployeeTable.getAirlineEmployeesByRole(airlineCode, role);
        }
    }
    
    public ResultSet showFlightData(String airlineCode) {
        if(!airlineTable.findByPrimaryKey(airlineCode)) {
            return null;
        } else {
            return airlineTable.showFlightInfo(airlineCode);
        }
    }
    
    public void payTax(String airlineCode, Date billingDate, Date paymentDate) {
        if(!airlineTable.findByPrimaryKey(airlineCode) || !(taxTable.findByPrimaryKey(airlineCode, billingDate))) {
        } else {
            taxTable.payAirlineTax(airlineCode, billingDate, paymentDate);
        }
    }
    
    public void insertNewPlane(String planeCode, String model, int seatsNumber, String airlineCode) {
        if(!(airlineTable.findByPrimaryKey(airlineCode) || (planeTable.findByPrimaryKey(planeCode)))) {
        } else {
            Plane plane = new Plane(planeCode, airlineCode, model, seatsNumber, false);
            planeTable.insertNewPlane(plane);
        } 
    }
    
    public int updatePlaneFlag(String planeCode) {
        if(!planeTable.findByPrimaryKey(planeCode)) {
            return 2;
        } else {
            return planeTable.updatePlaneMaintenanceFlag(planeCode);
        }
    }
    
    public void insertFlightService(String flightServiceId, String flightAirlineCode, String flightCode, String airlineCode, String employeeCode) {
        if((flightServiceTable.findByPrimaryKey(flightServiceId)) || !(airlineTable.findByPrimaryKey(flightAirlineCode)) || 
          !(airlineEmployeeTable.findByPrimaryKey(employeeCode, airlineCode)) || !(flightTable.findByPrimaryKey(flightCode, flightAirlineCode))) {
        } else {
            FlightService service = new FlightService(flightServiceId, flightAirlineCode, flightCode, airlineCode, employeeCode);
            flightServiceTable.addFlightService(service);
        }
    }
    
    public boolean insertNewAirportEmployee(String employeeCode, String name, String surname, String genderID, String city, String adress, String role, Optional<String> specificRole) {
        if(airportEmployeeTable.findByPrimaryKey(employeeCode)) {
            return false;
        } else {
            AirportEmployee airportEmployee;
            Employee.Gender gender;
            if(genderID.contentEquals("M")) {
                gender = Employee.Gender.M;
            } else {
                gender = Employee.Gender.F;
            }
            
            if(specificRole.isEmpty()) {
                Optional<String> optSpecific = Optional.empty();
                airportEmployee = new AirportEmployee(employeeCode, name, surname, gender, city, adress, role, optSpecific);
            } else {
                airportEmployee = new AirportEmployee(employeeCode, name, surname, gender, city, adress, role, specificRole);
            }
            
            return this.airportEmployeeTable.insertNewAirportEmployee(airportEmployee);    
        }   
    }
    
    public void insertNewTax(String airlineCode, Date billingDate) {
        if(taxTable.findByPrimaryKey(airlineCode, billingDate)) {
        } else {
            Tax tax = new Tax(airlineCode, billingDate);
            taxTable.insertNewTax(tax);
        }
    }
    
    public ResultSet showAirlineTaxes(String airlineCode) {
            return taxTable.showAirlineTaxes(airlineCode);
    }
    
    public ResultSet showAirlineYearlyPayments(String airlineCode, int year) {
        return taxTable.showAirlinePaidAmountByYear(airlineCode, year);
    }
    
    public ResultSet showAirportEmployeesByRole(String role) {
        return airportEmployeeTable.getEmployeesByRole(role);
    }
    
    public ResultSet airportTotalIncome() {
        return taxTable.showTotalIncome();
    }
    
    public ResultSet showAllMaintenance() {
        return maintenanceTable.showMaintenancePlanes();
    }
    
    public ResultSet airlineDestinations(String airlineCode) {
        return airlineTable.showAirlineDestinations(airlineCode);
    }
    
    public void insertNewMaintenance(String id, String planeCode, String employeeCode, Date startDate, Optional<Date> finishDate) {
        if((maintenanceTable.findByPrimaryKey(id)) || !(planeTable.findByPrimaryKey(planeCode)) || !(airportEmployeeTable.findByPrimaryKey(employeeCode))) {
        } else {
            Maintenance maintenance;
            if(finishDate.isPresent()) {
                maintenance = new Maintenance(id, employeeCode, planeCode, startDate, finishDate);
            } else {
                Optional<Date> emptyFinishDate = Optional.empty();
                maintenance = new Maintenance(id, employeeCode, planeCode, startDate, emptyFinishDate);
            }
             
            maintenanceTable.maintenancePlane(maintenance);
        } 
    }
    
    public ResultSet countPlaneTotalFlights(String planeCode) {
        return planeTable.showTotalPlaneFlights(planeCode);
    }
    
}
