package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordController2 implements Initializable {
    ResetPasswordController RPC = new ResetPasswordController();
    HomeScreenController HSC = new HomeScreenController();

    @ FXML private TextField NewPassword;

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        NewPassword.setText(RPC.getNewPassword());
    }

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(){
        HSC.HomeScene(HomeButton);
    }

    // loads Password reset scene 2
    public void NextScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ResetPassword2.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading 2nd reset password");
        }
    }
}
