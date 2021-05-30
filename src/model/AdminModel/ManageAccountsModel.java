package model.AdminModel;

import controller.utils.IntegerCheckUtil;
import dao.EmployeeDAO;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.LoginModel;

public class ManageAccountsModel {
    EmployeeDAO EDAO = new EmployeeDAO();
    IntegerCheckUtil ICU = new IntegerCheckUtil();

    public void GetAllAccounts(TextArea textArea, TextArea textArea2){ // displays a list of admin accounts
        EDAO.updateEmployee();
        if(EDAO.getListOfAdmins().size() == 0){
            textArea.setText("No Admin accounts found");
            textArea2.setText("No employee accounts found");
        }else{
            for(int i = 0; i < EDAO.getListOfAdmins().size(); i++){
                String tmp = "Name: " + EDAO.getListOfAdmins().get(i).getFullName() + "\n" +
                        "Status: " + EDAO.getListOfAdmins().get(i).getStatus() + "\n" +
                        "Account ID: " + EDAO.getListOfAdmins().get(i).getID() + "\n" + "" + "\n";
                textArea.appendText(tmp);
            }

            for(int i = 0; i < EDAO.getListOfEmployees().size(); i++){
                String tmp2 = "Name: " + EDAO.getListOfEmployees().get(i).getFullName() + "\n" +
                        "Status: " + EDAO.getListOfEmployees().get(i).getStatus() + "\n" +
                        "Account ID: " + EDAO.getListOfEmployees().get(i).getID() + "\n" + "" + "\n";
                textArea2.appendText(tmp2);
            }
        }
    }

    public int ToInt(String AccountID){ //convert TextArea input into integer
        return ICU.verifyInteger(AccountID);
    }

    public boolean CheckInvalidInput(String AccountID, Label label){ //check the input is valid & account ID exists
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
        }else{
            ValidInput = true;
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
