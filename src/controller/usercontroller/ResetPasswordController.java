package controller.usercontroller;

import controller.HomeScreenController;
import controller.utils.StringCheck;
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

public class ResetPasswordController {
    @FXML private TextField SQ;
    @FXML private TextField SQConfirm;
    @FXML private TextField SQ_A;

    @FXML private Label SQError;
    @FXML private Label SQ_AError;
    @FXML private Label ErrorMessage;

    HomeScreenController HSC = new HomeScreenController();
    StringCheck StringCheck = new StringCheck();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

    @FXML
    private Button ContinueButton;
    public void setContinueButtonClick(ActionEvent event){
        ResetPasswordScene(ContinueButton);
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

    // button that takes user to new scene
    @FXML
    private Button ResetPasswordButton;
    public void setResetPasswordButtonClick(ActionEvent event){
        ResetPassword();
    }

    // program logic
    private void ResetPassword(){
        boolean error1, error2, error3;
        error1 = StringCheck.InputNotEmpty(SQ, SQError);
        error2 = StringCheck.InputNotEmpty(SQ_A, SQError);

    }



}
