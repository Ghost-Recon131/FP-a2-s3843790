package controller;

import controller.utils.StringCheck;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.ResetPasswordModel;
import java.sql.SQLException;

public class ResetPasswordController {
    @FXML private TextField SQ;
    @FXML private TextField SQ_A;

    @FXML private Label SQError;
    @FXML private Label SQAError;
    @FXML private Label ErrorMessage;
    private static String NewPassword; // allows it to be passed to next scene very easily

    HomeScreenController HSC = new HomeScreenController();
    StringCheck StringCheck = new StringCheck();
    ResetPasswordModel ResetPasswordModel = new ResetPasswordModel();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

    // button activates the reset password process
    @FXML
    public Button ResetPasswordButton;
    public void setResetPasswordButtonClick(ActionEvent event) {
        try {
            ResetPassword(event);
        } catch (SQLException e) {
            System.err.println("Exception occurred in password reset");
        }
    }

    // program logic for checking and resetting password
    private void ResetPassword(Event event) throws SQLException {
        boolean error1, error2, error3;

        // check that the entered information matches database
        if (!ResetPasswordModel.LocateUser(SQ.getText().toUpperCase(), SQ_A.getText())){
            SQError.setText("Secret question or answer is wrong");
            ErrorMessage.setText("Failed to reset password");
            error3 = true;
        }else{
            SQError.setText("");
            ErrorMessage.setText("");
            error3 = false;
        }

        error1 = StringCheck.InputNotEmpty(SQ, SQError);
        error2 = StringCheck.InputNotEmpty(SQ_A, SQAError);

        // proceed when there's no errors
        if (!error1 && !error2 && !error3){
            ResetPasswordModel.ResetPassword(SQ_A.getText());
            NewPassword = ResetPasswordModel.getNewPassword();
            ResetPasswordController2 RPC = new ResetPasswordController2();
            RPC.NextScene(event);
        }
    }

    public String getNewPassword(){ // pass new password to next scene
        return NewPassword;
    }

}
