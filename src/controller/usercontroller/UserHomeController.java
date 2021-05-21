package controller.usercontroller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // allow other classes to switch back to UserHome scene
    public void UserHomeScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/userview/UserHome.fxml"));
            Parent root = (Parent) loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading User Home view");
        }
    }


}
