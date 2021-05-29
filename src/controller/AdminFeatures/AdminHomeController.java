package controller.AdminFeatures;

import controller.HomeScreenController;
import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.AdminModel.AdminHomeModel;
import model.UserModel.UserHomeModel;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    @FXML private Label AdminName, COVIDNotifications, GeneralNotifications;

    AdminHomeModel AHM = new AdminHomeModel();
    UserHomeModel UHM = new UserHomeModel();
    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        AdminName.setText(AHM.getAdminName());
        COVIDNotifications.setText(UHM.getCOVIDNotification() + "\n Admin Message: \n" + "- " +UHM.GetGlobalAdminMessage());
    }

    @FXML
    private Button LogoutButton; // takes user back to home essentially logging out since theres no way back
    public void LogoutButtonClick(){
        HomeScreenController HSC = new HomeScreenController();
        HSC.HomeScene(LogoutButton);
    }

    @FXML
    private void ManageCOVIDRestrictions(Event event){
        CSU.ChangeScene(event, "/view/AdminView/COVIDRestrictions.fxml");
    }

    @FXML
    private void ManageAccounts(Event event){
        CSU.ChangeScene(event, "/view/AdminView/ManageAccounts.fxml");
    }

    @FXML
    private void GenerateCSVReport(Event event){
        CSU.ChangeScene(event, "/view/AdminView/GenerateCSVReport.fxml");
    }

    @FXML
    private void ViewBookings(Event event){
        CSU.ChangeScene(event, "/view/AdminView/ManageViewBookings.fxml");
    }

}
