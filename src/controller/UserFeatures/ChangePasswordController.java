package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.StringCheckUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UserModel.ChangePasswordModel;

public class ChangePasswordController {
    @FXML private TextField Password;
    @FXML private TextField NewPassword;
    @FXML private TextField ConfirmPassword;
    @FXML private Label PasswordError;
    @FXML private Label NewPasswordError;
    @FXML private Label ConfirmPasswordError;
    @FXML private Label ChangePasswordError;

    ChangeSceneUtil CSU = new ChangeSceneUtil();
    ChangePasswordModel CPM = new ChangePasswordModel();
    StringCheckUtil SCU = new StringCheckUtil();

    @FXML
    public void setBackButtonClick(Event event){ // goes back to employee menu
        CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
    }

    @FXML
    public void setLogoutButtonClick(Event event){ // goes back to Home Screen
        CSU.ChangeScene(event,"/view/HomeScreen.fxml");
    }

    @FXML
    public void setUpdatePasswordButtonClick(Event event){
        boolean error1, error2, error3, error4;
        error1 = SCU.InputNotEmpty(Password, PasswordError);
        error2 = SCU.InputNotEmpty(NewPassword, NewPasswordError);
        error3 = SCU.InputNotEmpty(ConfirmPassword, ConfirmPasswordError);

            // check that the new password and confirm password is equal
        if (!NewPassword.getText().equals(ConfirmPassword.getText()) || !SCU.VerifyString(ConfirmPassword.getText())) {
            ConfirmPasswordError.setText("Password does not match!");
            error4 = true;
        } else {
            error4 = false;
        }

        if(!error1 && !error2 && !error3 && !error4){
            CPM.ChangePassword(ConfirmPassword.getText());
            CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
        }else{
            ChangePasswordError.setText("Failed to register");
        }

    }


}
