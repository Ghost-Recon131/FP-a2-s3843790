package controller.UserFeatures;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateBookingController implements Initializable {
    UserHomeController UHC = new UserHomeController();

    @Override // loads in some values without user interaction
    public void initialize(URL url, ResourceBundle rb) {
        //todo if necessary
    }

    // method for switching to Update booking view
    public void UpdateBookingScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserView/UpdateBooking.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading Update booking view");
        }
    }

}
