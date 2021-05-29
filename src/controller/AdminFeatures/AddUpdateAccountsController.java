package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class AddUpdateAccountsController {
    @FXML private Label FirstnameError, LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError;
    @FXML private Label SQAError, AccountIDError, ActionError;
    @FXML private TextArea AccountID, Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA;

    ChangeSceneUtil CSU = new ChangeSceneUtil();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event, "/view/AdminView/ManageAccounts.fxml");
    }

    @FXML
    public void setAdminHomeButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }
    //todo implement add / updating of admin accounts
}
