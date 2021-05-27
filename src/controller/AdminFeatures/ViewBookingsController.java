package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.TableStatusUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import model.AdminModel.ViewBookingsModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewBookingsController implements Initializable {
    private LocalDate SelectedDate = LocalDate.now();
    @FXML private Rectangle R1, R2, R3, R4, R5, R6, R7, R8, R9, R10;
    @FXML private Label PickDate;
    @FXML private TextField Info1, Info2, Info3, Info4, Info5, Info6, Info7, Info8, Info9, Info10;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    TableStatusUtil TSU = new TableStatusUtil();
    ViewBookingsModel VBM = new ViewBookingsModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTables();
        PickDate.setText("Currently viewing bookings on : " + SelectedDate.toString());
        DisplayBookingInformation();
    }

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    public void UpdateTables(){
        TSU.SetTableColour(R1, 1, SelectedDate); // sets correct colours
        TSU.SetTableColour(R2, 2, SelectedDate);
        TSU.SetTableColour(R3, 3, SelectedDate);
        TSU.SetTableColour(R4, 4, SelectedDate);
        TSU.SetTableColour(R5, 5, SelectedDate);
        TSU.SetTableColour(R6, 6, SelectedDate);
        TSU.SetTableColour(R7, 7, SelectedDate);
        TSU.SetTableColour(R8, 8, SelectedDate);
        TSU.SetTableColour(R9, 9, SelectedDate);
        TSU.SetTableColour(R10, 10, SelectedDate);
    }

    @FXML private DatePicker Date; // get the date selected by the user
    public void setDatePickerAction(){
        SelectedDate = Date.getValue();
        PickDate.setText("Currently viewing bookings on : " + SelectedDate.toString());
        UpdateTables();
        DisplayBookingInformation();
    }

    public void DisplayBookingInformation(){
        VBM.GetBookingInfo(1, Info1, SelectedDate);
        VBM.GetBookingInfo(2, Info2, SelectedDate);
        VBM.GetBookingInfo(3, Info3, SelectedDate);
        VBM.GetBookingInfo(4, Info4, SelectedDate);
        VBM.GetBookingInfo(5, Info5, SelectedDate);
        VBM.GetBookingInfo(6, Info6, SelectedDate);
        VBM.GetBookingInfo(7, Info7, SelectedDate);
        VBM.GetBookingInfo(8, Info8, SelectedDate);
        VBM.GetBookingInfo(9, Info9, SelectedDate);
        VBM.GetBookingInfo(10, Info10, SelectedDate);
    }

}
