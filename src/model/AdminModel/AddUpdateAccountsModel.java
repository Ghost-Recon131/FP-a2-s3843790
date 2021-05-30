package model.AdminModel;

import controller.utils.StringCheckUtil;
import dao.EmployeeDAO;
import javafx.scene.control.Label;
import model.RegisterModel;

import java.sql.SQLException;

public class AddUpdateAccountsModel {
    EmployeeDAO EDAO = new EmployeeDAO();
    RegisterModel registerModel = new RegisterModel();
    ManageAccountsModel MAM = new ManageAccountsModel();
    StringCheckUtil SCU = new StringCheckUtil();

    //passes information to checking methods before proceeding with creating account
    public void CreateEmployeeAccount(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                                      Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){

        if(CanProceed(Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA, FirstnameError, LastnameError,
                UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError)){

            EDAO.updateEmployee();
            EDAO.addAccount(Firstname, Lastname, Username, Password, SQ, SQA);
            ClearErrorMessage(FirstnameError, LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError);
            ActionError.setText("New employee account created!");
        }else{
            ActionError.setText("Failed to create employee account!");
        }
    }

    //passes information to checking methods before proceeding with creating account
    public void CreateAdminAccount(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                                   Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){

        if(CanProceed(Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA, FirstnameError, LastnameError,
                UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError)){

            EDAO.updateEmployee();
            EDAO.addAdmin(Firstname, Lastname, Username, Password, SQ, SQA);
            ClearErrorMessage(FirstnameError, LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError);
            ActionError.setText("New admin account created!");
        }else{
            ActionError.setText("Failed to create admin account!");
        }
    }

    //passes information to checking methods before proceeding with updating account
    public void UpdateAccount(String AccountID, String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label AccountIDError, Label FirstnameError, Label LastnameError, Label UsernameError,
                              Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){

        boolean IsValidID = MAM.CheckInvalidInput(AccountID, AccountIDError);
        int FormattedAccountID = MAM.ToInt(AccountID);

        if(CanProceed(Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA, FirstnameError, LastnameError,
                UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError) && IsValidID){

            EDAO.updateEmployee();
            EDAO.updateAccount(FormattedAccountID, Firstname, Lastname, Username, Password, SQ, SQA);
            ClearErrorMessage(FirstnameError, LastnameError, UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError);
            ActionError.setText("Account updated!");
        }else{
            ActionError.setText("Failed to update account!");
        }
    }

    //check all required fields are filled
    private boolean InputNotEmpty(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                               Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError){

        boolean input1 = SCU.StringInputNotEmpty(Firstname, FirstnameError);
        boolean input2 = SCU.StringInputNotEmpty(Lastname, LastnameError);
        boolean input3 = SCU.StringInputNotEmpty(Username, UsernameError);
        boolean input4 = SCU.StringInputNotEmpty(Password, PasswordError);
        boolean input5 = SCU.StringInputNotEmpty(ConfirmPassword, ConfirmPasswordError);
        boolean input6 = SCU.StringInputNotEmpty(SQ, SQError);
        boolean input7 = SCU.StringInputNotEmpty(SQA, SQAError);

        return !input1 && !input2 && !input3 && !input4 && !input5 && !input6 && !input7;
    }

    //checks there are no accounts with the same first and last name
    private boolean UniqueName(String Firstname, String Lastname, Label FirstnameError, Label LastnameError){
        boolean UniqueName = false;
        if(registerModel.UniqueName(Firstname, Lastname)){
            UniqueName = true;
        }else{
            FirstnameError.setText("Account with the same name is already registered");
            LastnameError.setText("Account with the same name is already registered");
        }
        return UniqueName;
    }

    //checks that the username does not already exist
    private boolean UniqueUsername(String Username, Label UsernameError){
        boolean UniqueUsername = false;
        try{
            if(registerModel.UniqueUsername(Username.toUpperCase())){
                UniqueUsername = true;
            }else{
                UsernameError.setText("This username already exists");
            }
        }catch (SQLException e){
            return false;
        }
        return UniqueUsername;
    }

    //checks that the password and confirm password fields match
    private boolean PasswordsMatch(String Password, String ConfirmPassword, Label PasswordError, Label ConfirmPasswordError){
        boolean PasswordsMatch = false;
        if(Password.equals(ConfirmPassword)){
            PasswordsMatch = true;
        }else{
            PasswordError.setText("Passwords do not match!");
            ConfirmPasswordError.setText("Passwords do not match!");
        }
        return PasswordsMatch;
    }

    //helps check everything is fine
    private boolean CanProceed(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                                   Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError){

        boolean IsInputs, IsUniqueName, IsUniqueUsername, IsPasswordsMatch;

        IsInputs = InputNotEmpty(Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA, FirstnameError, LastnameError,
                UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError);
        IsUniqueName = UniqueName(Firstname, Lastname, FirstnameError, LastnameError);
        IsUniqueUsername = UniqueUsername(Username, UsernameError);
        IsPasswordsMatch = PasswordsMatch(Password, ConfirmPassword, PasswordError, ConfirmPasswordError);

        return IsInputs && IsUniqueName && IsUniqueUsername && IsPasswordsMatch;
    }

    private void ClearErrorMessage(Label FirstnameError, Label LastnameError, Label UsernameError, Label PasswordError,
                                   Label ConfirmPasswordError, Label SQError, Label SQAError){
        FirstnameError.setText("");
        LastnameError.setText("");
        UsernameError.setText("");
        PasswordError.setText("");
        ConfirmPasswordError.setText("");
        SQError.setText("");
        SQAError.setText("");
    }

}
