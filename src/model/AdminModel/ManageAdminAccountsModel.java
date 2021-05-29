package model.AdminModel;

import controller.utils.IntegerCheckUtil;
import dao.EmployeeDAO;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.LoginModel;

public class ManageAdminAccountsModel {
    EmployeeDAO EDAO = new EmployeeDAO();
    IntegerCheckUtil ICU = new IntegerCheckUtil();

    public void GetAllAdminAccount(TextArea textArea){ // displays a list of admin accounts
        EDAO.updateEmployee();
        if(EDAO.getListOfAdmins().size() == 0){
            textArea.setText("No Admin accounts found");
        }else{
            for(int i = 0; i < EDAO.getListOfAdmins().size(); i++){
                String tmp = "\n" + "Name: " + EDAO.getListOfAdmins().get(i).getFullName() + "\n" +
                        "Status: " + EDAO.getListOfAdmins().get(i).getStatus() + "\n" +
                        "Account ID: " + EDAO.getListOfAdmins().get(i).getID();
                textArea.appendText(tmp);
            }
        }
    }

    private int ToInt(String AccountID){ //convert TextArea input into integer
        return ICU.verifyInteger(AccountID);
    }

    private boolean CheckInvalidInput(String AccountID, Label label){ //check the input is valid & account ID exists
        boolean ValidInput = false;
        EDAO.updateEmployee();
        if(ToInt(AccountID) == -1){
            label.setText("Please enter numbers only!");
            return false;
        }

        //prevent any possible errors from an admin deleting their own account
        if(!EDAO.VerifyAccountID(ToInt(AccountID)) && ToInt(AccountID) != LoginModel.getCurrentUserID()){
            label.setText("Please check the entered AccountID");
            return false;
        }

        if(EDAO.getRole(ToInt(AccountID)).equals("admin")){ // checks account is an admin account
            ValidInput = true;
        }else{
            label.setText("Please select an admin account");
        }
        return ValidInput;
    }

    public void ActivateAccount(String AccountID, Label label){ // set account status as active
        EDAO.updateEmployee();
        if(CheckInvalidInput(AccountID, label)){
            EDAO.ChangeAccountStatus(ToInt(AccountID), "active");
            label.setText("Account has been activated");
        }
    }

    public void SuspendAccount(String AccountID, Label label){ // set account status as suspended
        EDAO.updateEmployee();
        if(CheckInvalidInput(AccountID, label)){
            EDAO.ChangeAccountStatus(ToInt(AccountID), "suspended");
            label.setText("Account has been suspended");
        }
    }

    public void DeleteAdminAccount(String AccountID, Label label){ // finalises the deletion
        if(CheckInvalidInput(AccountID, label)){
            EDAO.updateEmployee();
            EDAO.deleteAccount(ToInt(AccountID));
            label.setText("Account has been deleted");
        }
    }

}
