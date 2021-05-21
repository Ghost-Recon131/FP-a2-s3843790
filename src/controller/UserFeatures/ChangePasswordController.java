package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordController {
    @FXML private TextField Password;
    @FXML private TextField NewPassword;
    @FXML private TextField ConfirmPassword;

    UserHomeController UHC = new UserHomeController();
    ChangeSceneUtil CSU = new ChangeSceneUtil();

    // method for switching to Update booking view
    public void ChangePasswordScene(Event event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserView/ChangePassword.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading Change password view");
        }
    }

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }


}
