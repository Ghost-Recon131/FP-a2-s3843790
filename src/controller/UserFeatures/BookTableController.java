package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookTableController implements Initializable {
    private LocalDate SelectedDate;
    @FXML private Label DateError;
    @FXML private Label TableError;
    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @Override // loads in some values without user interaction
    public void initialize(URL url, ResourceBundle rb) {
       //todo if necessary
    }
    //todo Implement controller & model
    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML private DatePicker Date; // get the date selected by the user
    public void setDatePickerAction(){
        SelectedDate = Date.getValue();
        LocalDate ChangeFormat = LocalDate.parse(Date.getValue().toString());
        System.err.println("Selected date: " + SelectedDate);
        System.err.println("Format to LocalDate: " + ChangeFormat);
    }



}
