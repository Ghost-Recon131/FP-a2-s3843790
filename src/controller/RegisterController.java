package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {

    @FXML
    private Button LoginButton;
    public void setLoginButtonClick(ActionEvent event){
        Scene scene = LoginButton.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Login");
        }
    }

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        Scene scene = HomeButton.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/HomeScreen.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Home");
        }
    }

}
