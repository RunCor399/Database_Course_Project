package it.unibo.bd.view;

import java.io.IOException;

import it.unibo.bd.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SelectSceneController {
    private Stage stage;
    private MainController controller;
    
    public void setStage(Stage stage) {
        this.stage = stage;
        controller = new MainController();
    }
    
    @FXML
    public void goToAirlineLogin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("/airlineLoginGUI.fxml").openStream());
        AirlineLoginSceneController airlineLoginSceneController = loader.getController();
        airlineLoginSceneController.setStage(this.stage);
        airlineLoginSceneController.setController(this.controller);

        stage.setTitle("Login Compagnia");
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    @FXML
    public void goToAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("/adminTemplate.fxml").openStream());
        AdminSceneController adminSceneController = loader.getController();
        adminSceneController.setStage(this.stage);
        adminSceneController.setController(this.controller);

        stage.setTitle("Admin");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
