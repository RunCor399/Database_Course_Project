package it.unibo.bd.view;

import java.io.IOException;

import it.unibo.bd.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AirlineLoginSceneController {
    private Stage stage;
    private MainController controller;
    
    @FXML
    private TextField loginAirlineCode;
    
    @FXML
    private Label errorLoginLabel;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void setController(MainController controller) {
        this.controller = controller;
    }
    
    @FXML
    public void checkAirlineCode(ActionEvent actionEvent) throws IOException {
        String code = this.loginAirlineCode.getText();
        if(controller.checkAirlineCode(code)) {
            this.goToAirline(code);
        } else {
            this.errorLoginLabel.setText("La compagnia inserita non esiste.");
        }
    }
    
    
    public void goToAirline(String airlineCode) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("/airlineTemplate.fxml").openStream());
        AirlineSceneController airlineSceneController = loader.getController();
        airlineSceneController.setStage(this.stage);
        airlineSceneController.setData(airlineCode, this.controller);

        stage.setTitle("Compagnia Aerea");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
