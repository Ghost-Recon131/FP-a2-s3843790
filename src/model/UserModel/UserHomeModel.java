package model.UserModel;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import javafx.scene.control.Label;
import model.LoginModel;

import java.time.LocalDate;

public class UserHomeModel {
    private String COVIDStatus;
    EmployeeDAO EDAO = new EmployeeDAO();
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();

    public String getEmployeeName(){
        EDAO.updateEmployee();
        return EDAO.getEmployee(LoginModel.getCurrentUserID());
    }

    // shows current lockdown status
    public String getCOVIDNotification(){
        TDAO.updateTables();
        if(TDAO.getCOVID(-1) == 0){ //table number -1 is used for "global" COVID status and messages by admin
            COVIDStatus = "- No lockdown";
        } else if (TDAO.getCOVID(-1) == 1){
            COVIDStatus = "- Partial lockdown";
        } else if (TDAO.getCOVID(-1) == 2){
            COVIDStatus = "- Total Lockdown";
        }
        return COVIDStatus;
    }

    // shows default admin message regarding COVID lockdown of the office
    public String GetGlobalAdminMessage(){
        TDAO.updateTables();
        return TDAO.getAdminMessage(-1);
    }

    public void FinaliseCheckIn(int TableNumber){
        BDAO.updateBookings();
        EDAO.updateEmployee();
        BDAO.CheckIn(getCurrentBookingID());
        EDAO.setPreviousTableNumber(TableNumber, LoginModel.getCurrentUserID());
    }

    public int getCurrentBookingID(){ // returns the user's booking ID
        BDAO.updateBookings();
        return BDAO.getBookingID(LoginModel.getCurrentUserID());
    }

}
