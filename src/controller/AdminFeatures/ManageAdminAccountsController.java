package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.AdminModel.ManageAdminAccountsModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageAdminAccountsController implements Initializable {
    @FXML private TextArea AdminAccounts, GetAccountID;
    @FXML private Button ConfirmDeleteAccount;
    @FXML private Label Error, DeleteAccountError;
    private boolean Confirm = false;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    ManageAdminAccountsModel MAM = new ManageAdminAccountsModel();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        GetAllAdminAccount();
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

    public void GetAllAdminAccount(){
        MAM.GetAllAdminAccount(AdminAccounts);
    }

    @FXML
    public void setActivateAccountClick(){
        MAM.ActivateAccount(GetAccountID.getText(), Error);
    }

    @FXML
    public void setSuspendAccountClick(){
        MAM.SuspendAccount(GetAccountID.getText(), Error);
    }

    @FXML
    public void setDeleteAccountClick(){
        Confirm = true;
        ConfirmDeleteAccount.setVisible(true);
    }

    @FXML
    public void setConfirmDeleteAccountClick(){
        if(Confirm){
            MAM.DeleteAdminAccount(GetAccountID.getText(), DeleteAccountError);
        }
    }

    @FXML
    public void setAddAdminAccountClick(){
        //todo
    }

    @FXML
    public void setUpdateAdminAccountClick(){
        //todo
    }

}
