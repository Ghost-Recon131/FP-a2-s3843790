package controller;

import controller.utils.StringCheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.RegisterModel;
import java.sql.SQLException;


public class RegisterController {
    @FXML private TextField Firstname;
    @FXML private TextField Lastname;
    @FXML private TextField Username;
    @FXML private TextField Password;
    @FXML private TextField ConfirmPassword;
    @FXML private TextField SQ;
    @FXML private TextField SQ_A;

    @FXML private Label FirstnameError;
    @FXML private Label LastnameError;
    @FXML private Label UsernameError;
    @FXML private Label PasswordError;
    @FXML private Label ConfirmPasswordError;
    @FXML private Label SQError;
    @FXML private Label SQ_AError;
    @FXML private Label RegisterError;

    LoginController LC = new LoginController();
    HomeScreenController HSC = new HomeScreenController();
    RegisterModel registerModel = new RegisterModel();
    StringCheck StringCheck = new StringCheck();

    // sets error messages when information is missing
    public boolean InputNotEmpty(TextField Variable, Label ErrorVariable){
        boolean Error = true;
        if(!StringCheck.VerifyString(Variable.getText())){
            ErrorVariable.setText("This cannot be empty");
        }
        else{
            Error = false;
        }
        return Error;
    }

    // go to Login scene
    @FXML
    private Button LoginButton;
    public void setLoginButtonClick(ActionEvent event){
        LC.LoginScene(LoginButton);
    }

    // go to Home scene
    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }


    // button for creating new account
    @FXML
    private Button RegisterAccountButton;
    public void setRegisterAccountButton(ActionEvent event){
        RegisterNewAccount();
    }

    // checks for any errors then passes information onto DAO to enter into database
    public void RegisterNewAccount(){
        boolean error1, error2, error3, error4, error5, error6, error7, error8, error9;
        error1 = InputNotEmpty(Firstname, FirstnameError);
        error2 = InputNotEmpty(Lastname, LastnameError);
        error3 = InputNotEmpty(Username, UsernameError);
        error4 = InputNotEmpty(Password, PasswordError);
        error5 = InputNotEmpty(SQ, SQError);
        error6 = InputNotEmpty(SQ_A, SQ_AError);
        try { // checks that passwords match
            if (!Password.getText().equals(ConfirmPassword.getText())|| !StringCheck.VerifyString(ConfirmPassword.getText())) {
                ConfirmPasswordError.setText("Password does not match!");
                error7 = true;
            } else {
                error7 = false;
            }

            //ensure the username is unique
            if (!registerModel.UniqueUsername(Username.getText().toUpperCase())) {
                UsernameError.setText("Username Already Exists!");
                error8 = true;
            } else {
                UsernameError.setText("");
                error8 = false;
            }
            //ensure the username is unique
            if(!registerModel.UniqueName(Firstname.getText().toUpperCase(), Lastname.getText().toUpperCase())){
                FirstnameError.setText("An account with this name already exists");
                LastnameError.setText("An account with this name already exists");
                error9 = true;
            } else{
                FirstnameError.setText("");
                LastnameError.setText("");
                error9 = false;
            }

            if (!error1 && !error2 && !error3 && !error4 && !error5 && !error6 && !error7 && !error8 && !error9) {
                registerModel.RegisterAccount(Firstname.getText().toUpperCase(), Lastname.getText().toUpperCase(),
                        Username.getText().toUpperCase(), Password.getText(), SQ.getText().toUpperCase(), SQ_A.getText());

                HSC.HomeScene(HomeButton); // take user back to home
            } else {
                RegisterError.setText("Failed to register");
            }
        } catch (SQLException e) {
            System.err.println("Exception occurred in account registration");
        }
    }


}
