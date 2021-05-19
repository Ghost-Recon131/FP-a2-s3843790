package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/HomeScreen.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            System.err.println("Error in  GUI");
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        //temporary code for testing
    }
}
