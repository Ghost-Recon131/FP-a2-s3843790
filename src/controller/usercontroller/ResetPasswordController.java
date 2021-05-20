package controller.usercontroller;

import controller.HomeScreenController;
import controller.utils.StringCheck;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.usermodel.ResetPasswordModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ResetPasswordController {
    @FXML private TextField SQ;
    @FXML private TextField SQ_A;

    @FXML private Label SQError;
    @FXML private Label ErrorMessage;

    HomeScreenController HSC = new HomeScreenController();
    StringCheck StringCheck = new StringCheck();
    ResetPasswordModel ResetPasswordModel = new ResetPasswordModel();
    FinalResetPasswordController FRPC = new FinalResetPasswordController();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

    // button activates the reset password process
    @FXML
    private Button ResetPasswordButton;
    public void setResetPasswordButtonClick(ActionEvent event) {
        try {
            ResetPassword();
        } catch (SQLException e){
            System.err.println("Exception in reset password");
        }

    }

    // program logic for checking and resetting password
    private void ResetPassword() throws SQLException {
        boolean error1, error2, error3;

        // check that the entered information matches database
        if (ResetPasswordModel.LocateUser(SQ.getText(), SQ_A.getText())){
            SQError.setText("Secret question or answer is wrong");
            ErrorMessage.setText("Failed to reset password");
            error3 = true;
        } else{
            error3 = false;
        }

        error1 = StringCheck.InputNotEmpty(SQ, SQError);
        error2 = StringCheck.InputNotEmpty(SQ_A, SQError);

        // proceed when there's no errors
        if (!error1 && !error2 && !error3){
            ResetPasswordModel.ResetPassword(SQ_A.getText());
            FRPC.ResetPasswordScene(ResetPasswordButton);
        }

    }


}
