package controller.AdminFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.StringCheckUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AdminModel.COVIDRestrictionsModel;

import java.net.URL;
import java.util.ResourceBundle;

public class COVIDRestrictionsController implements Initializable {
    @FXML private Label CustomMessageError, COVIDStatus, LockDownStatus1, LockDownStatus2, LockDownStatus3, Table;
    @FXML private TextField COVIDNotifications, TableNumber;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    COVIDRestrictionsModel CRM = new COVIDRestrictionsModel();
    StringCheckUtil SCU = new StringCheckUtil();

    @Override // loads in some values as soon as user gets to home page
    public void initialize(URL url, ResourceBundle rb) {
        CRM.getCovidStatus(COVIDStatus);
    }

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/AdminView/AdminHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML // removes COVID restriction on all tables
    public void setNoRestriction(){
        CRM.RemoveLockdown();
        LockDownStatus1.setText("successfully set to no restrictions!");
        LockDownStatus2.setText("");
        LockDownStatus3.setText(""); // clear potentially showing fields
    }

    @FXML // sets tables 2, 4, 6, 8 and 10 to COVID lockdown
    public void setHalfCapacity(){ // active bookings on these tables will also be cancelled
        CRM.PartialLockdown();
        LockDownStatus2.setText("successfully set to 50% capacity");
        LockDownStatus3.setText("");
        LockDownStatus1.setText("");
    }

    @FXML // set all table to lockdown mode and no bookings allowed.
    public void setTotalLockdown(){ // all active bookings are cancelled
        CRM.TotalLockdown();
        LockDownStatus3.setText("successfully set to total lockdown");
        LockDownStatus1.setText("");
        LockDownStatus2.setText("");
    }

    @FXML
    public void setCustomMessage(){
        boolean Empty = SCU.InputNotEmpty(COVIDNotifications, CustomMessageError);
        if(!Empty){
            CRM.SetCustomMessage(COVIDNotifications.getText());
            CustomMessageError.setText("New message set");
        }
    }

    @FXML
    private void LockDownSpecificTable(){ // pass values to methods in model
        if(CRM.CheckTable(TableNumber.getText(), Table)){
            CRM.LockTable(CRM.ToInt(TableNumber.getText()), Table);
        }
    }

    @FXML
    private void ReleaseSpecificTable(){
        if(CRM.CheckTable(TableNumber.getText(), Table)){
            CRM.ReleaseTable(CRM.ToInt(TableNumber.getText()), Table);
        }
    }

}
