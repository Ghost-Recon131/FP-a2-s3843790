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

public class ResetPasswordController { //todo fix reset password controller
    @FXML private TextField SQ, SQ_A, Password;
    @FXML private Label SQError, SQAError, ErrorMessage, SuccessLabel, Success2;

    HomeScreenController HSC = new HomeScreenController();
    StringCheck StringCheck = new StringCheck();
    ResetPasswordModel ResetPasswordModel = new ResetPasswordModel();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(){
        HSC.HomeScene(HomeButton);
    }

    @FXML
    public Button ResetPasswordButton; // button activates the reset password process
    public void setResetPasswordButtonClick(ActionEvent event) {
        try {
            ResetPassword();
        } catch (SQLException e) {
            System.err.println("Exception occurred in password reset");
        }
    }

    // program logic for checking and resetting password
    private void ResetPassword() throws SQLException {
        boolean error1, error2, error3 = true;
        // check that the entered information matches database
        if (!ResetPasswordModel.LocateUser(SQ.getText().toUpperCase(), SQ_A.getText())){
            SQError.setText("Secret question or answer is wrong");
            ErrorMessage.setText("Failed to reset password");
        }else{
            SQError.setText("");
            ErrorMessage.setText("");
            error3 = false;
        }

        error1 = StringCheck.InputNotEmpty(SQ, SQError);
        error2 = StringCheck.InputNotEmpty(SQ_A, SQAError);

        if (!error1 && !error2 && !error3){ // proceed when there's no errors
            String NewPassword = ResetPasswordModel.getNewPassword();
            ResetPasswordModel.ResetPassword(SQ_A.getText());
            ResetPasswordButton.setVisible(false);
            SuccessLabel.setVisible(true);
            Success2.setVisible(true);
            Password.setVisible(true);
            Password.setText(NewPassword);
        }
    }


}
