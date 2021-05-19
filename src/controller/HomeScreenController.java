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

public class HomeScreenController {
    LoginController LC = new LoginController();

    @FXML
    private Button LoginButton;
    public void setLoginButtonClick(ActionEvent event){
        LC.LoginScene(LoginButton);
    }

    @FXML
    private Button RegisterButton;
    public void setRegisterButtonClick(ActionEvent event){
        LC.RegisterScene(RegisterButton);
    }

    public void HomeScene(Button button){
        Scene scene = button.getScene(); // get the current scene from the button
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
