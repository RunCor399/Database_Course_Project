package it.unibo.bd.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import it.unibo.bd.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AirlineSceneController {
    Stage stage;
    private String airlineCode;
    private MainController controller;
    
    @FXML
    private TextField airlineEmployeeRoleSearch;
    
    @FXML
    private TextField insertAirlineEmployeeName;
    
    @FXML
    private TextField insertAirlineEmployeeSurname;
    
    @FXML
    private TextField insertAirlineEmployeeCity;
    
    @FXML
    private TextField insertAirlineEmployeeAdress;
    
    @FXML
    private TextField insertAirlineEmployeeRole;
    
    @FXML
    private TextField insertAirlineEmployeeRank;
    
    @FXML
    private TextField insertFlightCode;
    
    @FXML
    private TextField insertFlightDepartureICAO;
    
    @FXML
    private TextField insertFlightArrivalICAO;
    
    @FXML
    private TextField insertPlaneCode;
    
    @FXML
    private TextField insertPlaneModel;
    
    @FXML
    private TextField insertPlaneSeatsNumber;
    
    @FXML
    private TextField insertAirlineEmployeeCode;
    
    @FXML
    private TextField payTaxBillingDate;
    
    @FXML
    private TextField payTaxPaymentDate;
    
    @FXML
    private TextField yearlyPaymentYear;
    
    @FXML
    private TextField insertFlightDepartureDate;
    
    @FXML
    private TextField insertFlightDepartureTime;
    
    @FXML
    private TextField insertFlightArrivalDate;
    
    @FXML
    private TextField insertFlightArrivalTime;
    
    @FXML
    private TextField insertFlightPlaneCode;
    
    @FXML
    private TextField updatePlaneStatusPlaneCode;
    
    @FXML
    private TextField insertFlightServiceCode;
    
    @FXML
    private TextField insertFlightServiceFlightCode;
    
    @FXML
    private TextField insertFlightServiceEmployeeCode;
    
    @FXML
    private TextField insertFlightCalendarID;
    
    @FXML
    private RadioButton insertAirlineEmployeeGenderM;
    
    @FXML
    private RadioButton insertAirlineEmployeeGenderF;
    
    @FXML
    private Label currentLoggedAirline;
    
    @FXML
    private Label updatePlaneStatusLabel;
    
    @FXML
    private Label yearlyPaymentLabel;
    
    @FXML
    private TextArea flightServiceTableFlightID;
    
    @FXML
    private TextArea flightServiceTableOrigin;
    
    @FXML
    private TextArea flightServiceTableDestination;
    
    @FXML
    private TextArea flightServiceTableName;
    
    @FXML
    private TextArea flightServiceTableSurname;
    
    @FXML
    private TextArea flightServiceTableRole;
    
    @FXML
    private TextArea flightServiceTableRank;
    
    @FXML
    private TextArea airlineEmployeeTableID;
    
    @FXML
    private TextArea airlineEmployeeTableName;
    
    @FXML
    private TextArea airlineEmployeeTableSurname;
    
    @FXML
    private TextArea airlineEmployeeTableGender;
    
    @FXML
    private TextArea airlineEmployeeTableCity;
    
    @FXML
    private TextArea airlineEmployeeTableRank;
    
    public void initialize() {
        this.flightServiceTableOrigin.setEditable(false);
        this.flightServiceTableDestination.setEditable(false);
        this.flightServiceTableName.setEditable(false);
        this.flightServiceTableSurname.setEditable(false);
        this.flightServiceTableRole.setEditable(false);
        this.flightServiceTableRank.setEditable(false);
        
        this.airlineEmployeeTableID.setEditable(false);
        this.airlineEmployeeTableName.setEditable(false);
        this.airlineEmployeeTableSurname.setEditable(false);
        this.airlineEmployeeTableGender.setEditable(false);
        this.airlineEmployeeTableCity.setEditable(false);
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setData(String code, MainController controller) {
        this.airlineCode = code;
        this.controller = controller;
        this.currentLoggedAirline.setText("Compagnia aerea: " + airlineCode);

    }
    
    @FXML
    public void insertAirlineEmployee() {
        String employeeCode = this.insertAirlineEmployeeCode.getText();
        String employeeName = this.insertAirlineEmployeeName.getText();
        String employeeSurname = this.insertAirlineEmployeeSurname.getText();
        String employeeGender;
        if(this.insertAirlineEmployeeGenderF.isSelected()) {
            employeeGender = "F";
        } else {
            employeeGender = "M";
        }
        String employeeCity = this.insertAirlineEmployeeCity.getText();
        String employeeAdress = this.insertAirlineEmployeeAdress.getText();
        String employeeRole = this.insertAirlineEmployeeRole.getText();
        Optional<String> employeeRank;
        if(this.insertAirlineEmployeeRank.getText().isBlank()) {
            employeeRank = Optional.empty();
        } else {
            String rank = this.insertAirlineEmployeeRank.getText();
            employeeRank = Optional.of(rank);
        }
        
        this.controller.insertNewAirlineEmployee(employeeCode, this.airlineCode, employeeName, employeeSurname, employeeGender, employeeCity, employeeAdress, employeeRole, employeeRank);
        
        this.insertAirlineEmployeeCode.clear();
        this.insertAirlineEmployeeName.clear();
        this.insertAirlineEmployeeSurname.clear();
        this.insertAirlineEmployeeCity.clear();
        this.insertAirlineEmployeeAdress.clear();
        this.insertAirlineEmployeeRole.clear();
        this.insertAirlineEmployeeRank.clear();
        this.insertAirlineEmployeeGenderF.setDisable(true);
        this.insertAirlineEmployeeGenderM.setDisable(true);
    }
    
    @FXML
    public void insertPlane() {
        String planeCode = this.insertPlaneCode.getText();
        String planeModel = this.insertPlaneModel.getText();
        int planeSeats = Integer.valueOf(this.insertPlaneSeatsNumber.getText());
        
        this.controller.insertNewPlane(planeCode, planeModel, planeSeats, this.airlineCode);
        
        this.insertPlaneCode.clear();
        this.insertPlaneModel.clear();
        this.insertPlaneSeatsNumber.clear();
    }
    
    @FXML
    public void insertFlight() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy:hh:mm");
        String flightCode = this.insertFlightCode.getText();
        String flightDepartureICAO = this.insertFlightDepartureICAO.getText();
        String flightArrivalICAO = this.insertFlightArrivalICAO.getText();
        String flightPlaneCode = this.insertFlightPlaneCode.getText();
        String flightCalendarID = this.insertFlightCalendarID.getText();
        
        String departureDate = this.insertFlightDepartureDate.getText();
        String departureTime = this.insertFlightDepartureTime.getText();
        Date flightDepartureDate = sdf.parse(departureDate + ":" + departureTime);
        
        Optional<Date> flightArrivalDate;

        if((this.insertFlightArrivalDate.getText().isBlank()) || (this.insertFlightArrivalTime.getText().isBlank())) {
            flightArrivalDate = Optional.empty();
        } else {
            String arrivalDate = this.insertFlightArrivalDate.getText();
            String arrivalTime = this.insertFlightArrivalTime.getText();
            flightArrivalDate = Optional.of(sdf.parse(arrivalDate + ":" + arrivalTime));
        }
        
        this.controller.insertNewFlight(this.airlineCode, flightCode, flightDepartureICAO, flightArrivalICAO, flightPlaneCode, flightCalendarID, flightDepartureDate, flightArrivalDate);
        
        this.insertFlightCode.clear();
        this.insertFlightDepartureICAO.clear();
        this.insertFlightArrivalICAO.clear();
        this.insertFlightPlaneCode.clear();
        this.insertFlightCalendarID.clear();
        this.insertFlightDepartureDate.clear();
        this.insertFlightDepartureTime.clear();
        this.insertFlightArrivalDate.clear();
        this.insertFlightArrivalTime.clear();
    }
    
    @FXML
    public void insertFlightService() {
        String flightServiceID = this.insertFlightServiceCode.getText();
        String flightServiceFlightCode = this.insertFlightServiceFlightCode.getText();
        String flightServiceEmployeeCode = this.insertFlightServiceEmployeeCode.getText();
        
        this.controller.insertFlightService(flightServiceID, this.airlineCode, flightServiceFlightCode, this.airlineCode, flightServiceEmployeeCode);
        
        this.insertFlightServiceCode.clear();
        this.insertFlightServiceFlightCode.clear();
        this.insertFlightServiceEmployeeCode.clear();
    }
    
    @FXML
    public void payTax() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        Date billingDate = sdf.parse(this.payTaxBillingDate.getText());
        Date paymentDate = sdf.parse(this.payTaxPaymentDate.getText());
        
        this.controller.payTax(this.airlineCode, billingDate, paymentDate);
        
        this.payTaxBillingDate.clear();
        this.payTaxPaymentDate.clear();
    }
    
    @FXML
    public void yearlyPayments() throws SQLException {
        int year = Integer.valueOf(this.yearlyPaymentYear.getText());
        
        ResultSet result = this.controller.showAirlineYearlyPayments(this.airlineCode, year);
        while(result.next()) {
            this.yearlyPaymentLabel.setText(result.getString(1) + "€");
        }
        this.yearlyPaymentYear.clear();
    }
    
    @FXML
    public void updatePlaneMaintenance() {
        String planeCode = this.updatePlaneStatusPlaneCode.getText();
        int status = this.controller.updatePlaneFlag(planeCode);
        
        if(status == 1) {
            this.updatePlaneStatusLabel.setText("L'aereo " + planeCode + "\nnon e' piu' da manutenere");
        } else if(status == 0){
            this.updatePlaneStatusLabel.setText("L'aereo " + planeCode + "\ne' da manutenere");
        } else {
            this.updatePlaneStatusLabel.setText("L'aereo " + planeCode + " non esiste");
        }
        this.updatePlaneStatusPlaneCode.clear();
    }
    
    @FXML
    public void showAirlineEmployees() throws SQLException {
        String role = this.airlineEmployeeRoleSearch.getText();
        ResultSet result = this.controller.showEmployeesByRole(this.airlineCode, role);
        
        this.airlineEmployeeTableID.clear();
        this.airlineEmployeeTableName.clear();
        this.airlineEmployeeTableSurname.clear();
        this.airlineEmployeeTableGender.clear();
        this.airlineEmployeeTableCity.clear();
        this.airlineEmployeeTableRank.clear();
        
        this.airlineEmployeeTableID.appendText("Codice\n");
        this.airlineEmployeeTableName.appendText("Nome\n");
        this.airlineEmployeeTableSurname.appendText("Cognome\n");
        this.airlineEmployeeTableGender.appendText("Genere\n");
        this.airlineEmployeeTableCity.appendText("Citta'\n");
        this.airlineEmployeeTableRank.appendText("Grado\n");
        
        while(result.next()) {
            this.airlineEmployeeTableID.appendText(result.getString(1) + "\n");
            this.airlineEmployeeTableName.appendText(result.getString(2) + "\n");
            this.airlineEmployeeTableSurname.appendText(result.getString(3) + "\n");
            this.airlineEmployeeTableGender.appendText(result.getString(4) + "\n");
            this.airlineEmployeeTableCity.appendText(result.getString(5) + "\n");
            this.airlineEmployeeTableRank.appendText(result.getString(8) + "\n");
        }
    }
    
    @FXML
    public void showFlightService() throws SQLException {
        ResultSet result = this.controller.showFlightData(airlineCode);
        
        this.flightServiceTableFlightID.clear();
        this.flightServiceTableOrigin.clear();
        this.flightServiceTableDestination.clear();
        this.flightServiceTableName.clear();
        this.flightServiceTableSurname.clear();
        this.flightServiceTableRole.clear();
        this.flightServiceTableRank.clear();
        
        this.flightServiceTableFlightID.appendText("Codice Volo\n");
        this.flightServiceTableOrigin.appendText("Origine\n");
        this.flightServiceTableDestination.appendText("Destinazione\n");
        this.flightServiceTableName.appendText("Nome\n");
        this.flightServiceTableSurname.appendText("Cognome\n");
        this.flightServiceTableRole.appendText("Ruolo\n");
        this.flightServiceTableRank.appendText("Grado\n");
        
        while(result.next()) {
            this.flightServiceTableFlightID.appendText(result.getString(1) + "\n");
            this.flightServiceTableOrigin.appendText(result.getString(3) + "\n");
            this.flightServiceTableDestination.appendText(result.getString(4) + "\n");
            this.flightServiceTableName.appendText(result.getString(6) + "\n");
            this.flightServiceTableSurname.appendText(result.getString(7) + "\n");
            this.flightServiceTableRole.appendText(result.getString(8) + "\n");
            this.flightServiceTableRank.appendText(result.getString(9) + "\n");
        }
    }
}
