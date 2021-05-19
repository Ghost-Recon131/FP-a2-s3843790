package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterController {
    LoginController LC = new LoginController();
    HomeScreenController HSC = new HomeScreenController();

    @FXML
    private Button LoginButton;
    public void setLoginButtonClick(ActionEvent event){
        LC.LoginScene(LoginButton);
    }

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

}
