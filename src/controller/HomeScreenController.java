package controller;

import dao.BookingsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {
    LoginController LC = new LoginController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeOutBookings(); // ran as the program is started for maximum efficiency and rule out possible edge cases
    } // that may occur if it is ran anywhere else in the program

    // helps to automatically cancel bookings once they reached their reserve data but the admin still hasn't approved it
    private void TimeOutBookings(){
        try{
            BookingsDAO BDAO = new BookingsDAO();
            BDAO.updateBookings();
            BDAO.AutoCancelBookings();
        }catch(Exception e){
            // catch block is necessary to remove Concurrent Accesses error from being printed in the terminal
            // even though the code works as expected and does not crash the program without the try catch block.
        }
    }

    @FXML
    private Button LoginButton;
    public void setLoginButtonClick(){
        LC.LoginScene(LoginButton);
    }

    @FXML
    private Button RegisterButton;
    public void setRegisterButtonClick(){
        LC.RegisterScene(RegisterButton);
    }

    public void HomeScene(Button button){
        Scene scene = button.getScene(); // get the current scene from the button
        Window window = scene.getWindow();
        Stage primaryStage = (Stage) window;

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/HomeScreen.fxml"))); // get Login.fxml
            primaryStage.setScene(new Scene(root, 1280, 720));
        } catch (IOException e) {
            System.out.println("Failed to load Home");
        }
    }

}
