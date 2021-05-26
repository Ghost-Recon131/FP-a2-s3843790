package model.AdminModel;

import dao.TableDAO;
import javafx.scene.control.Label;
import model.UserModel.UserHomeModel;

public class COVIDRestrictionsModel {
    TableDAO TDAO = new TableDAO();
    UserHomeModel UHM = new UserHomeModel();

    public void getCovidStatus(Label label){
        label.setText(UHM.getCOVIDNotification());
    }

    public void RemoveLockdown(){
        TDAO.LockdownAllTables();
        TDAO.removeLockdown();
    }

    public void PartialLockdown(){
        TDAO.updateTables();
        TDAO.PartialLockdown();
    }

    public void TotalLockdown(){
        TDAO.updateTables();
        TDAO.LockdownAllTables();
    }

    public void SetCustomMessage(String NewMessage){
        TDAO.updateTables();
        TDAO.UpdateAdminMessage(-1, NewMessage);
    }

}
