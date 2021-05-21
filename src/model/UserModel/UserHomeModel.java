package model.UserModel;

import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import model.LoginModel;

public class UserHomeModel {
    private String COVIDStatus;
    private boolean ActiveBooking;
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

    public boolean HasBooking(){
        try{
            BDAO.updateBookings();
            int BookingID = BDAO.getBookingID(LoginModel.getCurrentUserID());
            boolean Status1 = BDAO.getBookingStatus(BookingID).equals("cancelled");
            boolean Status2 = BDAO.getBookingStatus(BookingID).equals("completed");
            if(BookingID != -1 && !Status1 && !Status2){
                ActiveBooking = true;
            }
            return ActiveBooking;
        }catch(Exception e){
            return ActiveBooking;
        }
    }

}
