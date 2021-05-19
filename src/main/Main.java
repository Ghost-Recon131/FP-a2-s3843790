package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/HomeScreen.fxml")));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            System.err.println("Error in launching GUI");
        }
    }

    public static void main(String[] args) {
        launch(args);
        //temporary code for testing
    }
}
