package controller.usercontroller;

import controller.HomeScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;

public class FinalResetPasswordController {
    @FXML private Label NewPassword;
    HomeScreenController HSC = new HomeScreenController();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

    // method for switching to final password reset scene
    public void ResetPasswordScene(Button button) {
        Scene scene = button.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/userview/FinalResetPassword.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Confirmation page for reset password");
        }
    }

}
