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

public class BookTableController implements Initializable {
    UserHomeController UHC = new UserHomeController();

    @Override // loads in some values without user interaction
    public void initialize(URL url, ResourceBundle rb) {
       //todo if necessary
    }

    // method for switching to Book Table view
    public void BookTableScene(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserView/BookTable.fxml"));
            Parent root = loader.load();
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch (IOException e){
            System.err.println("Exception loading Book table screen");
        }
    }

}
