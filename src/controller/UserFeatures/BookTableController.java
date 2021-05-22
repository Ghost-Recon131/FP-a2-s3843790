package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BookTableController implements Initializable {
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



}
