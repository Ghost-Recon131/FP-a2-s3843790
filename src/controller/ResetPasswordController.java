package controller;

import controller.HomeScreenController;
import controller.utils.StringCheck;
import javafx.event.ActionEvent;
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
    @FXML private Label ResetText;
    @FXML private Label ResetText2;
    @FXML private TextField NewPassword;

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
            ResetPassword();
        } catch (SQLException e) {
            System.err.println("Exception occurred in password reset");
        }
    }

    // program logic for checking and resetting password
    private void ResetPassword() throws SQLException {
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
            ResetText.setText("Your password has been reset!");
            ResetText2.setText("Your new password is: ");
            NewPassword.setVisible(true);
            NewPassword.setText(ResetPasswordModel.getNewPassword());
        }
    }

}
