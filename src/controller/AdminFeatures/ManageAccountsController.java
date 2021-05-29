package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.AdminModel.ManageAccountsModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAccountsController implements Initializable {
    @FXML private TextArea AdminAccounts, EmployeeAccounts, GetAccountID;
    @FXML private Button ConfirmDeleteAccount;
    @FXML private Label Error, DeleteAccountError;
    private boolean Confirm = false;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    ManageAccountsModel MAM = new ManageAccountsModel();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        GetAllAccounts();
    }

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }
    //todo implement manage admin accounts

    public void GetAllAccounts(){
        MAM.GetAllAccounts(AdminAccounts, EmployeeAccounts);
    }

    @FXML
    public void Refresh(Event event){
        CSU.ChangeScene(event, "/view/AdminView/ManageAccounts.fxml");
    }

    @FXML
    public void setActivateAccountClick(Event event){
        MAM.ActivateAccount(GetAccountID.getText(), Error);
        Refresh(event);
    }

    @FXML
    public void setSuspendAccountClick(Event event){
        MAM.SuspendAccount(GetAccountID.getText(), Error);
        Refresh(event);
    }

    @FXML
    public void setDeleteAccountClick(){
        if(MAM.CheckInvalidInput(GetAccountID.getText(), DeleteAccountError)){
            Confirm = true;
            ConfirmDeleteAccount.setVisible(true);
        }
    }

    @FXML
    public void setConfirmDeleteAccountClick(Event event){
        if(Confirm){
            MAM.DeleteAdminAccount(GetAccountID.getText(), DeleteAccountError);
            Refresh(event);
        }
    }

    @FXML
    public void setAddUpdateAccountClick(Event event){
        CSU.ChangeScene(event, "/view/AdminView/AddUpdateAccounts.fxml");
    }

}
