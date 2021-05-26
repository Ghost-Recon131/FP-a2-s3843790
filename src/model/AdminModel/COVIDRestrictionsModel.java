package model.AdminModel;

import controller.utils.IntegerCheckUtil;
import dao.TableDAO;
import javafx.scene.control.Label;
import model.UserModel.UserHomeModel;

public class COVIDRestrictionsModel {
    TableDAO TDAO = new TableDAO();
    UserHomeModel UHM = new UserHomeModel();
    IntegerCheckUtil ICU = new IntegerCheckUtil();

    public void getCovidStatus(Label label){
        label.setText(UHM.getCOVIDNotification());
    }

    public void RemoveLockdown(){
        TDAO.updateTables();
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

    public boolean CheckTable(String TableNumber, Label label){
        int tmp = ICU.verifyInteger(TableNumber);
        boolean check = false;
        if(tmp >= 1 && tmp <= 10){
            check = true;
        }else{
            label.setText("Please enter a valid table number between 1-10");
        }
        System.err.println(tmp);
        System.err.println(check);
        return check; //-1 is the default error return, so if it is not -1 and between 1-10 it will return true
    }

    public int ToInt(String TableNumber){
        return ICU.verifyInteger(TableNumber);
    }

    public void ReleaseTable(int TableNumber, Label label){
        TDAO.updateTables();
        TDAO.releaseSpecificTable(TableNumber);
        label.setText("Table " + TableNumber + " is released from lockdown");
    }

    public void LockTable(int TableNumber, Label label){
        TDAO.updateTables();
        TDAO.lockdownSpecificTable(TableNumber);
        label.setText("Table " + TableNumber + " has been locked down");
    }

}
