package main;

import dao.EmployeeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.SHAHash;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/login.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        launch(args);

        //temp code for testing
//        EmployeeModel EM = new EmployeeModel(2, "homey", "ash", "HAsh", "HELLO",
//                "employee", "What is your favourite pet's name?", "Toby");
//
//        System.out.println(EM.getPassword());
//        System.out.println(EM.getSQAnswer());
//
//        SHAHash Hash = new SHAHash();
//        String test = "3733cd977ff8eb18b987357e22ced99f46097f31ecb239e878ae63760e83e4d5";
//        System.out.println(Hash.getHash(test));
//
//        EmployeeDAO ED = new EmployeeDAO();
//        ED.updateEmployee();
//        if(EM.getPassword().equals(ED.getPassword(1))){
//            System.out.println("Success!");
//        }
//        else{
//            System.out.println(ED.getPassword(1));
//            System.out.println("Something went wrong");
//        }


    }
}
