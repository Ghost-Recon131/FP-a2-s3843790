package model.AdminModel;

import controller.utils.StringCheckUtil;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.File;

public class GenerateCSVReportModel {
    BookingsDAO BDAO = new BookingsDAO();
    EmployeeDAO EDAO = new EmployeeDAO();
    StringCheckUtil SCU = new StringCheckUtil();


    public void GenerateEmployeeCSV(TextArea Filepath, Label label){
        EDAO.updateEmployee();
        if (CheckFilePath(Filepath, label, "/Employees.csv")) {
            EDAO.ExportEmployeesTable(Filepath.getText());
            label.setText("Successfully exported to filepath as Employees.csv");
        }
    }

    public void GenerateBookingsCSV(TextArea Filepath, Label label){
        BDAO.updateBookings();
        if (CheckFilePath(Filepath, label, "/Bookings.csv")) {
            BDAO.ExportBookingsTable(Filepath.getText());
            label.setText("Successfully exported to filepath as Bookings.csv");
        }
    }

    private boolean CheckFilePath(TextArea Filepath, Label label, String Type){ // checks that entry is not empty and filepath exists
        boolean ValidInput = false;
        try{
            if(SCU.VerifyString(Filepath.getText()) && new File(Filepath.getText()).exists()){
            ValidInput = true;
            }else{
                label.setText("This cannot be empty!");
            }

            if(new File(Filepath.getText() + Type).exists()){
                ValidInput = false;
                label.setText("The CSV report already exists, please move the current file");
            }

        }catch(Exception e){
            return false;
        }
        return ValidInput;
    }

}
