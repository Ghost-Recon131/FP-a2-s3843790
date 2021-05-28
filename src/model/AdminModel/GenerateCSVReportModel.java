package model.AdminModel;

import controller.utils.StringCheckUtil;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class GenerateCSVReportModel {
    BookingsDAO BDAO = new BookingsDAO();
    EmployeeDAO EDAO = new EmployeeDAO();
    StringCheckUtil SCU = new StringCheckUtil();

    public void GenerateEmployeeCSV(TextArea Filepath, Label label){
        EDAO.updateEmployee();
        //todo
    }

    public void GenerateBookingsCSV(TextArea Filepath, Label label){
        BDAO.updateBookings();
        //todo
    }

    private boolean CheckFilePath(TextArea Filepath, Label label){
        boolean ValidInput = false;
        if(SCU.VerifyString(Filepath.getText())){
            ValidInput = true;
        }else{
            label.setText("This cannot be empty!");
            return false;
        }

//        if(true){//todo Check filepath exists & handle errors accordingly
//            //
//         }

        return ValidInput;
    }

}
