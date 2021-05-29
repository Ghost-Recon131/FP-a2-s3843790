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
                String tmp = "Name: " + EDAO.getListOfAdmins().get(i).getFullName() + "\n" +
                        "Status: " + EDAO.getListOfAdmins().get(i).getStatus() + "\n" +
                        "Account ID: " + EDAO.getListOfAdmins().get(i).getID() + "\n";
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
        }else{
            ValidInput = true;
        }

        //prevent any possible errors from an admin deleting their own account
        if(!EDAO.VerifyAccountID(ToInt(AccountID)) && ToInt(AccountID) != LoginModel.getCurrentUserID()){
            label.setText("Please check the entered AccountID");
            ValidInput = false;
        }
        return ValidInput;
    }

    public void DeleteAdminAccount(String AccountID, Label label){ // finalises the deletion
        if(CheckInvalidInput(AccountID, label)){
            EDAO.updateEmployee();
            EDAO.deleteAccount(ToInt(AccountID));
            label.setText("Account has been deleted");
        }
    }

}
