package model.AdminModel;

import controller.utils.IntegerCheckUtil;
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
    IntegerCheckUtil ICU = new IntegerCheckUtil();

    //passes information to checking methods before proceeding with creating account
    public void CreateEmployeeAccount(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                                      Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){
    }

    //passes information to checking methods before proceeding with creating account
    public void CreateAdminAccount(String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label FirstnameError, Label LastnameError, Label UsernameError,
                                   Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){

    }

    //passes information to checking methods before proceeding with updating account
    public void UpdateAccount(String AccountID, String Firstname, String Lastname, String Username, String Password, String
            ConfirmPassword, String SQ, String SQA, Label AccountIDError, Label FirstnameError, Label LastnameError, Label UsernameError,
                              Label PasswordError, Label ConfirmPasswordError, Label SQError, Label SQAError, Label ActionError){

        boolean IsInputs, IsIDNotEmpty, IsValidID, IsUniqueName, IsUniqueUsername, IsPasswordsMatch;
        IsValidID = MAM.CheckInvalidInput(AccountID, AccountIDError);
        int FormattedAccountID = MAM.ToInt(AccountID);
        IsInputs = InputNotEmpty(Firstname, Lastname, Username, Password, ConfirmPassword, SQ, SQA, FirstnameError, LastnameError,
                UsernameError, PasswordError, ConfirmPasswordError, SQError, SQAError);

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
        return input1 && input2 && input3 && input4 && input5 && input6 && input7;
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

        boolean CanProceed = false;
        return CanProceed;
    }

}
