package controller;

import controller.utils.StringCheckUtil;
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
    StringCheckUtil SCU = new StringCheckUtil();

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
    public void setRegisterAccountButton(ActionEvent event) {
        try {
            RegisterNewAccount();
        } catch (SQLException e) {
            System.err.println("Exception occurred in account registration");
        }
    }

    // checks for any errors then passes information onto DAO to enter into database
    private void RegisterNewAccount() throws SQLException {
        boolean error1, error2, error3, error4, error5, error6, error7, error8, error9;

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

        error1 = SCU.InputNotEmpty(Firstname, FirstnameError);
        error2 = SCU.InputNotEmpty(Lastname, LastnameError);
        error3 = SCU.InputNotEmpty(Username, UsernameError);
        error4 = SCU.InputNotEmpty(Password, PasswordError);
        error5 = SCU.InputNotEmpty(SQ, SQError);
        error6 = SCU.InputNotEmpty(SQ_A, SQ_AError);
        try { // checks that passwords match
            if (!Password.getText().equals(ConfirmPassword.getText())|| !SCU.VerifyString(ConfirmPassword.getText())) {
                ConfirmPasswordError.setText("Password does not match!");
                error7 = true;
            } else {
                error7 = false;
            }
            // verify there are no errors remaining before proceeding
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
