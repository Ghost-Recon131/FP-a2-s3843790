package controller.UserFeatures;

import controller.HomeScreenController;
import controller.utils.ChangeSceneUtil;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.UserModel.UserHomeModel;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable{
    @FXML private Label COVIDNotifications;
    @FXML private Label GeneralNotifications;
    @FXML private Label CheckinError;
    @FXML private Label EmployeeName;

    UserHomeModel UHM = new UserHomeModel();
    ChangeSceneUtil CSU = new ChangeSceneUtil();
//    BookTableController BTC = new BookTableController(); // these cause problems
//    UpdateBookingController UBC = new UpdateBookingController();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        EmployeeName.setText(UHM.getEmployeeName());
        COVIDNotifications.setText(UHM.getCOVIDNotification() + "\n Admin Message: \n" + "- " +UHM.GetGlobalAdminMessage());
    }

    @FXML
    private Button LogoutButton; // takes user back to home essentially logging out since theres no way back
    public void LogoutButtonClick(){
        HomeScreenController HSC = new HomeScreenController();
        HSC.HomeScene(LogoutButton);
    }

    @FXML
    // determines which scene to display
    public void BookUpdateTableButtonClick(Event event){
        CSU.ChangeScene(event,"/view/UserView/BookTable.fxml");
//        if(UHM.HasBooking()){
//            System.out.println(" booking");
//
//       }else{
//         System.out.println("Not booked");
//        }
    }

    @FXML
    private Button ChangePasswordButton;
    public void ChangePasswordButtonClick(Event event){ // takes user to Change Password scene
        CSU.ChangeScene(event,"/view/UserView/ChangePassword.fxml");
    }

    @FXML
    public void CheckInButtonClick(Event event){ // no new scene here, just marks a booking as complete
        //todo
    }

}
