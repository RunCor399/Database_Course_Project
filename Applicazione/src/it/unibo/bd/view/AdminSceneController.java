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

public class AdminSceneController {
 private Stage stage;
 private MainController controller;
 
 @FXML
 private TextField airportEmployeesRole;
 
 @FXML
 private TextField insertAirportEmployeeName;
 
 @FXML
 private TextField insertAirportEmployeeSurname;
 
 @FXML
 private TextField insertAirportEmployeeCity;
 
 @FXML
 private TextField insertAirportEmployeeAdress;
 
 @FXML
 private TextField insertAirportEmployeeRole;
 
 @FXML
 private TextField insertAirportEmployeeSpecificRole;
 
 @FXML
 private TextField insertAirlineCode;
 
 @FXML
 private TextField insertAirlineName;
 
 @FXML
 private TextField insertAirlineHub;
 
 @FXML
 private TextField insertTaxAirlineCode;
 
 @FXML
 private TextField insertTaxBillingDate;
 
 @FXML
 private TextField insertMaintenanceID;
 
 @FXML
 private TextField insertMaintenancePlaneCode;
 
 @FXML
 private TextField insertMaintenanceEmployeeCode;
 
 @FXML
 private TextField insertMaintenanceStartDate;
 
 @FXML
 private TextField insertMaintenanceFinishDate;
 
 @FXML
 private TextField billsAirlineCode;
 
 @FXML
 private TextField insertAirportEmployeeCode;
 
 @FXML
 private TextField destinationAirlineCode;
 
 @FXML
 private TextField totalFlightsPlaneCode;
 
 @FXML
 private TextField airportIncomeYear;
 
 @FXML
 private TextField airlinePaymentsCode;
 
 @FXML
 private TextField airlinePaymentsYear;
 
 @FXML
 private RadioButton insertAirportEmployeeGenderM;
 
 @FXML
 private RadioButton insertAirportEmployeeGenderF;
 
 @FXML
 private Label totalFlightsPlaneLabel;
 
 @FXML
 private Label airportIncomeLabel;
 
 @FXML
 private Label airlinePaymentsLabel;
 
 @FXML
 private TextArea taxTableAirline;
 
 @FXML
 private TextArea taxTableBilling;
 
 @FXML
 private TextArea taxTablePayment;
 
 @FXML
 private TextArea taxTableAmount;
 
 @FXML
 private TextArea maintenanceTableID;
 
 @FXML
 private TextArea maintenanceTableAirline;
 
 @FXML
 private TextArea maintenanceTablePlane;
 
 @FXML
 private TextArea maintenanceTableEmployee;
 
 @FXML
 private TextArea maintenanceTableStartDate;
 
 @FXML
 private TextArea maintenanceTableFinishDate;

 @FXML
 private TextArea airportEmployeesTableID;
 
 @FXML
 private TextArea airportEmployeesTableName;
 
 @FXML
 private TextArea airportEmployeesTableSurname;
 
 @FXML
 private TextArea airportEmployeesTableGender;
 
 @FXML
 private TextArea airportEmployeesTableCity;
 
@FXML
private TextArea destinationTableICAO;

@FXML
private TextArea destinationTableCity;
 
    public void initialize() {
        this.airportEmployeesTableID.setEditable(false);
        this.airportEmployeesTableName.setEditable(false);
        this.airportEmployeesTableSurname.setEditable(false);
        this.airportEmployeesTableGender.setEditable(false);
        this.airportEmployeesTableCity.setEditable(false);
        
        this.taxTableAirline.setEditable(false);
        this.taxTableBilling.setEditable(false);
        this.taxTablePayment.setEditable(false);
        this.taxTableAmount.setEditable(false);
        this.taxTableAirline.setEditable(false);
        this.taxTableBilling.setEditable(false);
        this.taxTablePayment.setEditable(false);
        this.taxTableAmount.setEditable(false);
        
        this.maintenanceTableID.setEditable(false);
        this.maintenanceTableAirline.setEditable(false);
        this.maintenanceTablePlane.setEditable(false);
        this.maintenanceTableEmployee.setEditable(false);
        this.maintenanceTableStartDate.setEditable(false);
        this.maintenanceTableFinishDate.setEditable(false);
        
        this.destinationTableICAO.setEditable(false);
        this.destinationTableCity.setEditable(false);
    }
 
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setController(MainController controller) {
        this.controller = controller;
    }
    
    @FXML
    public void countTotalFlights() throws SQLException {
        String planeCode = this.totalFlightsPlaneCode.getText();
        ResultSet result;
        result = controller.countPlaneTotalFlights(planeCode);
        
        if(result.next()) {
            Integer value = result.getInt("totale_voli");
            this.totalFlightsPlaneLabel.setText(" " + Integer.toString(value) + " voli");
        }
    }
    
    @FXML
    public void insertAirportEmployee() {
        String employeeCode = this.insertAirportEmployeeCode.getText();
        String employeeName = this.insertAirportEmployeeName.getText();
        String employeeSurname = this.insertAirportEmployeeSurname.getText();
        String employeeGender;
        if(this.insertAirportEmployeeGenderF.isSelected()) {
            employeeGender = "F";
        } else {
            employeeGender = "M";
        }
        String employeeCity = this.insertAirportEmployeeCity.getText();
        String employeeAdress = this.insertAirportEmployeeAdress.getText();
        String employeeRole = this.insertAirportEmployeeRole.getText();
        Optional<String> specificRole;
        if(this.insertAirportEmployeeSpecificRole.getText().isBlank()) {
            specificRole = Optional.empty();
        } else {
            String specRole = this.insertAirportEmployeeSpecificRole.getText();
            specificRole = Optional.of(specRole);
        }
        
        this.controller.insertNewAirportEmployee(employeeCode, employeeName, employeeSurname, employeeGender, employeeCity, employeeAdress, employeeRole, specificRole);
        
        this.insertAirportEmployeeCode.clear();
        this.insertAirportEmployeeName.clear();
        this.insertAirportEmployeeSurname.clear();
        this.insertAirportEmployeeGenderF.setDisable(true);
        this.insertAirportEmployeeGenderM.setDisable(true);
        this.insertAirportEmployeeCity.clear();
        this.insertAirportEmployeeAdress.clear();
        this.insertAirportEmployeeRole.clear();
        this.insertAirportEmployeeSpecificRole.clear();
    }
    
    @FXML
    public void showAirportEmployeeTable() {
        
        String role = this.airportEmployeesRole.getText();
        ResultSet result = this.controller.showAirportEmployeesByRole(role);
        this.airportEmployeesTableID.clear();
        this.airportEmployeesTableName.clear();
        this.airportEmployeesTableSurname.clear();
        this.airportEmployeesTableGender.clear();
        this.airportEmployeesTableCity.clear();
        this.airportEmployeesTableID.appendText("Codice\n");
        this.airportEmployeesTableName.appendText("Nome\n");
        this.airportEmployeesTableSurname.appendText("Cognome\n");
        this.airportEmployeesTableGender.appendText("Genere\n");
        this.airportEmployeesTableCity.appendText("Citta'\n");
        
        
        try {
            while(result.next()) {
                this.airportEmployeesTableID.appendText(result.getString(1) + "\n");
                this.airportEmployeesTableName.appendText(result.getString(2) + "\n");
                this.airportEmployeesTableSurname.appendText(result.getString(3) + "\n");
                this.airportEmployeesTableGender.appendText(result.getString(4) + "\n");
                this.airportEmployeesTableCity.appendText(result.getString(5) + "\n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
    @FXML
    public void showBills() {
        String airlineCode = this.billsAirlineCode.getText();
        ResultSet result = this.controller.showAirlineTaxes(airlineCode);
        
        this.taxTableAirline.clear();
        this.taxTableBilling.clear();
        this.taxTablePayment.clear();
        this.taxTableAmount.clear();
        this.taxTableAirline.appendText("Compagnia\n");
        this.taxTableBilling.appendText("Data Fatturazione\n");
        this.taxTablePayment.appendText("Data Pagamento\n");
        this.taxTableAmount.appendText("Importo\n");

        
        try {
            while(result.next()) {
                this.taxTableAirline.appendText(result.getString(2) + "\n");
                this.taxTableBilling.appendText(result.getString(3) + "\n");
                this.taxTablePayment.appendText(result.getString(4) + "\n");
                this.taxTableAmount.appendText(result.getString(5) + "\n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void showMaintenance() {
        ResultSet result = this.controller.showAllMaintenance();
        
        this.maintenanceTableID.clear();
        this.maintenanceTableAirline.clear();
        this.maintenanceTablePlane.clear();
        this.maintenanceTableEmployee.clear();
        this.maintenanceTableStartDate.clear();
        this.maintenanceTableFinishDate.clear();
        
        this.maintenanceTableID.appendText("ID\n");
        this.maintenanceTableAirline.appendText("Compagnia\n");
        this.maintenanceTablePlane.appendText("Aereo\n");
        this.maintenanceTableEmployee.appendText("Dipendente\n");
        this.maintenanceTableStartDate.appendText("Data Inizio\n");
        this.maintenanceTableFinishDate.appendText("Data Fine\n");
        
        
        
        try {
            while(result.next()) {
                this.maintenanceTableID.appendText(result.getString(1) + "\n");
                this.maintenanceTableAirline.appendText(result.getString(2) + "\n");
                this.maintenanceTablePlane.appendText(result.getString(3) + "\n");
                this.maintenanceTableEmployee.appendText(result.getString(4) + "\n");
                this.maintenanceTableStartDate.appendText(result.getString(5) + "\n");
                this.maintenanceTableFinishDate.appendText(result.getString(6) + "\n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void showDestinations() {
        String airlineCode = this.destinationAirlineCode.getText();
        ResultSet result = this.controller.airlineDestinations(airlineCode);
        
        this.destinationTableICAO.clear();
        this.destinationTableCity.clear();
        
        this.destinationTableICAO.appendText("ICAO\n");
        this.destinationTableCity.appendText("Citta'\n");
        
     
        
        try {
            while(result.next()) {
                this.destinationTableICAO.appendText(result.getString(1) + "\n");
                this.destinationTableCity.appendText(result.getString(3) + "\n");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void insertMaintenance() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String maintenanceID = this.insertMaintenanceID.getText();
        String planeCode = this.insertMaintenancePlaneCode.getText();
        String employeeCode = this.insertMaintenanceEmployeeCode.getText();
        Date startDate = sdf.parse(this.insertMaintenanceStartDate.getText());
        
        Optional<Date> optFinishDate;
        
        if (this.insertMaintenanceFinishDate.getText().isBlank()) {
            optFinishDate = Optional.empty();
        } else {
            Date finishDate = sdf.parse(this.insertMaintenanceFinishDate.getText());
            optFinishDate = Optional.of(finishDate);
        }
       
        this.controller.insertNewMaintenance(maintenanceID, planeCode, employeeCode, startDate, optFinishDate);
        
        this.insertMaintenanceID.clear();
        this.insertMaintenancePlaneCode.clear();
        this.insertMaintenanceEmployeeCode.clear();
        this.insertMaintenanceStartDate.clear();
        this.insertMaintenanceFinishDate.clear();
    }
    
    @FXML
    public void insertAirline() {
        String airlineCode = this.insertAirlineCode.getText();
        String airlineName = this.insertAirlineName.getText();
        String airlineHub = this.insertAirlineHub.getText();
        
        this.controller.insertAirline(airlineCode, airlineName, airlineHub);
        
        this.insertAirlineCode.clear();
        this.insertAirlineName.clear();
        this.insertAirlineHub.clear();
    }
    
    @FXML
    public void insertTax() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        String airlineCode = this.insertTaxAirlineCode.getText();
        Date billingDate = sdf.parse(this.insertTaxBillingDate.getText());
        
        this.controller.insertNewTax(airlineCode, billingDate);
        
        this.insertTaxAirlineCode.clear();
        this.insertTaxBillingDate.clear();
    }
    
    @FXML
    public void totalIncome() throws SQLException {
        ResultSet result;
        result = controller.airportTotalIncome();
        this.airportIncomeLabel.setText("");
        
        if(result.next()) {
            Integer value = result.getInt("fatturato_totale");
            this.airportIncomeLabel.setText(Integer.toString(value) + "€");
        }
    }
    
    
    @FXML
    public void yearlyAirlinePayments() throws SQLException {
        String airlineCode = this.airlinePaymentsCode.getText();
        int year = Integer.valueOf(this.airlinePaymentsYear.getText());
        
        ResultSet result;
        result = controller.showAirlineYearlyPayments(airlineCode, year);
        this.airlinePaymentsLabel.setText("");
        
        if(result.next()) {
            Integer value = result.getInt("importo");
            this.airlinePaymentsLabel.setText(Integer.toString(value) + "€");
        }
    }
    
    
    
    

}
