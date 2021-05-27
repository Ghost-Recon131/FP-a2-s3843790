package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.TableStatusUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import model.AdminModel.ManageViewBookingsModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManageViewBookingsController implements Initializable {
    private LocalDate SelectedDate = LocalDate.now();
    @FXML private Rectangle R1, R2, R3, R4, R5, R6, R7, R8, R9, R10;
    @FXML private Label PickDate;
    @FXML private TextArea Info1, Info2, Info3, Info4, Info5, Info6, Info7, Info8, Info9, Info10;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    TableStatusUtil TSU = new TableStatusUtil();
    ManageViewBookingsModel VBM = new ManageViewBookingsModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTables();
        PickDate.setText("Currently viewing bookings on : " + SelectedDate.toString());
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
        TSU.AdminTableView(R1, 1, SelectedDate, Info1); // sets correct colours
        TSU.AdminTableView(R2, 2, SelectedDate, Info2);
        TSU.AdminTableView(R3, 3, SelectedDate, Info3);
        TSU.AdminTableView(R4, 4, SelectedDate, Info4);
        TSU.AdminTableView(R5, 5, SelectedDate, Info5);
        TSU.AdminTableView(R6, 6, SelectedDate, Info6);
        TSU.AdminTableView(R7, 7, SelectedDate, Info7);
        TSU.AdminTableView(R8, 8, SelectedDate, Info8);
        TSU.AdminTableView(R9, 9, SelectedDate, Info9);
        TSU.AdminTableView(R10, 10, SelectedDate, Info10);
    }

    @FXML private DatePicker Date; // get the date selected by the user
    public void setDatePickerAction(){
        SelectedDate = Date.getValue();
        PickDate.setText("Currently viewing bookings on : " + SelectedDate.toString());
        UpdateTables();
    }

}
