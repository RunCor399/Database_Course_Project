package it.unibo.bd.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class View extends Application {
    private Stage primaryStage;

    @FXML
    Button airlineLoginButton;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResource("/selectUser.fxml").openStream());

        SelectSceneController selectSceneController = loader.getController();
        selectSceneController.setStage(this.primaryStage);

        this.primaryStage.setTitle("Gestionale Aeroporto");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.show();
    }

   

    public static void main(final String[] args) {
        launch(args);
    }
}
