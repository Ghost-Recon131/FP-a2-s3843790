package controller;

import controller.usercontroller.ResetPasswordController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.LoginModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;


    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event){
        Login();
    }

    @FXML
    private Button RegisterButton; // takes user to registration page
    public void setRegisterButtonClick(ActionEvent event){
       RegisterScene(RegisterButton);
    }

    @FXML // take user to reset password page
    private Button ForgotPasswordButton;
    public void setForgotPasswordButtonClick(ActionEvent event){
        Scene scene = ForgotPasswordButton.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/userview/ResetPassword.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Reset password");
        }
    }

    Window window;
    public void Login(){
        Stage primaryStage = (Stage) window;
        try {
            if (loginModel.isLogin(txtUsername.getText().toUpperCase(),txtPassword.getText())){

                isConnected.setText("Logged in successfully");
            }else{
                isConnected.setText("Username or Password is incorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // method for changing to LoginScene
    public void LoginScene(Button button){
        Scene scene = button.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Login.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Login");
        }
    }

    // method for changing to Register scene
    public void RegisterScene(Button button){
        Scene scene = button.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/Register.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Register");
        }
    }

}
