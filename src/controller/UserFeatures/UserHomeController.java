package controller.UserFeatures;

import controller.HomeScreenController;
import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.UserModel.BookTableModel;
import model.UserModel.UserHomeModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable{
    @FXML private Label COVIDNotifications;
    @FXML private Label GeneralNotifications;
    @FXML private Label CheckinError;
    @FXML private Label EmployeeName;
    @FXML private Button CheckInButton;

    UserHomeModel UHM = new UserHomeModel();
    ChangeSceneUtil CSU = new ChangeSceneUtil();
    BookTableModel BTM = new BookTableModel();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        EmployeeName.setText(UHM.getEmployeeName());
        COVIDNotifications.setText(UHM.getCOVIDNotification() + "\n Admin Message: \n" + "- " +UHM.GetGlobalAdminMessage());
        if(!BTM.HasBooking()){
            CheckInButton.setVisible(false);
        }else{
            CheckInButton.setVisible(true);
        }
    }

    @FXML
    private Button LogoutButton; // takes user back to home essentially logging out since theres no way back
    public void LogoutButtonClick(){
        HomeScreenController HSC = new HomeScreenController();
        HSC.HomeScene(LogoutButton);
    }

    @FXML
    // displays the Book Table screen
    public void BookUpdateTableButtonClick(Event event){
        CSU.ChangeScene(event,"/view/UserView/BookTable.fxml");
    }

    @FXML
    public void ChangePasswordButtonClick(Event event){ // takes user to Change Password scene
        CSU.ChangeScene(event,"/view/UserView/ChangePassword.fxml");
    }

    @FXML
    public void CheckInButtonClick(Event event){ // no new scene here, just marks a booking as complete
        //todo
        boolean error1, error2, error3;
        error1 = BTM.HasBooking();
        error2 = BTM.BookingIsApproved();
        error3 = BTM.SittingDateIsNow();

        if(error1 && error2 && error3){
            UHM.FinaliseCheckIn(BTM.getTableNumber());
            GeneralNotifications.setText("Successfully checked in");
        }else{
            CheckinError.setText("Failed to check in, please ensure your booking is\n" +
                    "on the current day and it has been approved by admin.");
        }

    }

}
