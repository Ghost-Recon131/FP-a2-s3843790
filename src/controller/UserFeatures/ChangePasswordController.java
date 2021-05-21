package controller.UserFeatures;

import controller.utils.ChangeSceneUtil;
import controller.utils.StringCheck;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.UserModel.ChangePasswordModel;

import java.util.concurrent.TimeUnit;

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
    StringCheck StringCheck = new StringCheck();

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
        error1 = StringCheck.InputNotEmpty(Password, PasswordError);
        error2 = StringCheck.InputNotEmpty(NewPassword, NewPasswordError);
        error3 = StringCheck.InputNotEmpty(ConfirmPassword, ConfirmPasswordError);

        try{
            // check that the new password and confirm password is equal
            if (!NewPassword.getText().equals(ConfirmPassword.getText()) || !StringCheck.VerifyString(ConfirmPassword.getText())) {
                ConfirmPasswordError.setText("Password does not match!");
                error4 = true;
            } else {
                error4 = false;
            }

            if(!error1 && !error2 && !error3 && !error4){
                CPM.ChangePassword(ConfirmPassword.getText());
                TimeUnit.SECONDS.sleep(1);
                CSU.ChangeScene(event,"/view/UserView/userHome.fxml");
            }else{
                ChangePasswordError.setText("Failed to register");
            }
        }catch(InterruptedException e){
            System.err.println("Something went wrong in program hold");
        }
    }


}
