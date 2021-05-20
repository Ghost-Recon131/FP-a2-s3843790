package controller.usercontroller;

import controller.HomeScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FinalResetPasswordController {
    HomeScreenController HSC = new HomeScreenController();

    @FXML
    private Button HomeButton;
    public void setHomeButtonClick(ActionEvent event){
        HSC.HomeScene(HomeButton);
    }

}
