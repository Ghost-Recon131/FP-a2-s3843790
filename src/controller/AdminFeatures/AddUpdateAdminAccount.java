package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;

public class AddUpdateAdminAccount {
    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/ManageAdminAccounts.fxml");
    }

    @FXML
    public void setAdminHomeButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }
    //todo implement add / updating of admin accounts
}
