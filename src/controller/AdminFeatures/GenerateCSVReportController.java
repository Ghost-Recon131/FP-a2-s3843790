package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.AdminModel.GenerateCSVReportModel;

public class GenerateCSVReportController {
    @FXML private TextArea EmployeeCSV, BookingsCSV;
    @FXML private Label EmployeeCSVError, BookingsCSVError;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    GenerateCSVReportModel GCM = new GenerateCSVReportModel();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML
    public void GenerateEmployeeCSV(){ //passes values onto GenerateCSVReportModel to handle the back end logic
        GCM.GenerateEmployeeCSV(EmployeeCSV, EmployeeCSVError);
    }

    @FXML
    public void GenerateBookingsCSV(){ //passes values onto GenerateCSVReportModel to handle the back end logic
        GCM.GenerateBookingsCSV(BookingsCSV, BookingsCSVError);
    }

}
