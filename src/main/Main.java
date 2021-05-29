package main;
import dao.BookingsDAO;
import dao.EmployeeDAO;
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
            System.err.println("Error in GUI");
        }
    }

    public static void main(String[] args) {
        try{
            launch(args);
        }catch (Exception e){
            System.err.println("Error in GUI");
        }

        //temporary code for testing
//        EmployeeDAO EDAO = new EmployeeDAO();
//        for(int i = 0; i < EDAO.getListOfEmployees().size(); i++){ // gets list of employee accounts
//            String tmp = "Name: " + EDAO.getListOfEmployees().get(i).getFullName() + "\n" +
//                    "Status" + EDAO.getListOfEmployees().get(i).getStatus() + "\n" +
//                    "Account ID" + EDAO.getListOfEmployees().get(i).getID() + "\n";
//            System.out.println(tmp);
//        }

    }


}
