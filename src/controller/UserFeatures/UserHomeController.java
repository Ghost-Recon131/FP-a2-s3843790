package controller.UserFeatures;

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
import javafx.stage.Stage;
import javafx.stage.Window;
import model.UserModel.UserHomeModel;
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

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        EmployeeName.setText(UHM.getEmployeeName());
        COVIDNotifications.setText(UHM.getCOVIDNotification() + "\n Admin Message: \n" + "- " +UHM.GetGlobalAdminMessage());
    }

    // allow other classes to switch back to UserHome scene
    public void UserHomeScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserView/UserHome.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading User Home screen");
        }
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
//        BookTableController BTC = new BookTableController();
//        UpdateBookingController UBC = new UpdateBookingController();
//        if(UHM.HasBooking()){
//            UBC.UpdateBookingScene(event); // load UpdateBookingScene when there is existing booking
//        }else{
//            BTC.BookTableScene(event); // load BookTableScene when there is no existing / active booking
//        }
    }

    @FXML
    private Button ChangePasswordButton;
    public void ChangePasswordButtonClick(Event event){ // takes user to Change Password scene
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
    public void CheckInButtonClick(Event event){ // no new scene here, just marks a booking as complete
        //todo
    }

}
