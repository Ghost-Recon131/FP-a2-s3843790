package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.AdminModel.AddUpdateAccountsModel;

public class AddUpdateAccountsController {
    @FXML private Label FirstnameError, LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError;
    @FXML private Label SQAError, AccountIDError, ActionError;
    @FXML private TextArea AccountID, Firstname, Lastname, Username, SQ;
    @FXML private TextField Password, ConfirmPassword,SQA;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    AddUpdateAccountsModel AUM = new AddUpdateAccountsModel();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event, "/view/AdminView/ManageAccounts.fxml");
    }

    @FXML
    public void setAdminHomeButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setCreateEmployeeAccount(){
        AUM.CreateEmployeeAccount(Firstname.getText().toUpperCase(), Lastname.getText().toUpperCase(), Username.getText().toUpperCase(),
                Password.getText(), ConfirmPassword.getText(), SQ.getText().toUpperCase(), SQA.getText(), FirstnameError,
                LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError, ActionError);
    }

    public void setCreateAdminAccount(){
        AUM.CreateAdminAccount(Firstname.getText().toUpperCase(), Lastname.getText().toUpperCase(), Username.getText().toUpperCase(),
                Password.getText(), ConfirmPassword.getText(), SQ.getText().toUpperCase(), SQA.getText(), FirstnameError,
                LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError, ActionError);
    }

    public void setUpdateAccount(){
        AUM.UpdateAccount(AccountID.getText(), Firstname.getText().toUpperCase(), Lastname.getText().toUpperCase(),
                Username.getText().toUpperCase(), Password.getText(), ConfirmPassword.getText(), SQ.getText().toUpperCase(),
                SQA.getText(), AccountIDError, FirstnameError, LastnameError, UsernameError, PasswordError,
                ConfirmPasswordError, SQError, SQAError, ActionError);
    }

}
