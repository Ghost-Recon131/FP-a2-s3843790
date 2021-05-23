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
    BookTableModel BTM = new BookTableModel();

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

    public void FinaliseCheckIn(){
        BDAO.updateBookings();
        BDAO.CheckIn(BTM.getCurrentBookingID());
    }

    public boolean HasBooking(){
        return BTM.HasBooking();
    }

    public boolean BookingIsApproved(){
        return BDAO.getBookingStatus(BTM.getCurrentBookingID()).equals("approved");
    }

    public boolean SittingDateIsNow(){
        return BDAO.getSittingDate(BTM.getCurrentBookingID()).isEqual(LocalDate.now());
    }



}
