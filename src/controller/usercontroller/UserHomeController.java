package controller.usercontroller;

import controller.HomeScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.usermodel.UserHomeModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable{
    @FXML private TextField COVIDNotifications;
    @FXML private TextField GeneralNotifications;
    @FXML private Label CheckinError;
    @FXML private Label EmployeeName;
    HomeScreenController HSC = new HomeScreenController();
    UserHomeModel UHM = new UserHomeModel();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        EmployeeName.setText(UHM.getEmployeeName());
        COVIDNotifications.setText(UHM.getCOVIDNotification() );
//        + "\n" + UHM.GetGlobalAdminMessage()
    }

    // allow other classes to switch back to UserHome scene
    public void UserHomeScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/userview/UserHome.fxml"));
            Parent root = (Parent) loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading User Home screen");
        }
    }

    @FXML private Button LogoutButton; // takes user back to home essentially logging out since theres no way back
    public void LogoutButtonClick(Event event){
        HSC.HomeScene(LogoutButton);
    }

}