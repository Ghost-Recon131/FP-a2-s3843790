package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class COVIDRestrictionsController implements Initializable {
    @FXML private Label CustomMessageError;
    @FXML private TextField COVIDStatus;

    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        //todo get the current covid restriction status and display it
    }

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML
    public void setNoRestriction(Event event){ // goes back to Home Screen
        //todo
    }

    @FXML
    public void setHalfCapacity(Event event){ // goes back to Home Screen
        //todo
    }

    @FXML
    public void setTotalLockdown(Event event){ // goes back to Home Screen
        //todo
    }

    //todo implement enacting COVID restrictions
}
