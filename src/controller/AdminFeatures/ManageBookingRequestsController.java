package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;

public class ManageBookingRequestsController {
    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }
    //todo implement manage bookings
}
