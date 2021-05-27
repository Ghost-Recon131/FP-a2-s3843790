package model.AdminModel;

import controller.utils.TableStatusUtil;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class ViewBookingsModel {
    BookingsDAO BDAO = new BookingsDAO();
    EmployeeDAO EDAO = new EmployeeDAO();
    TableStatusUtil TSU = new TableStatusUtil();

    public void GetBookingInfo(int TableNumber, TextField textfield, LocalDate Date){
        BDAO.updateBookings();
        EDAO.updateEmployee();

        int BookingID = BDAO.getBookingIDUsingTableNumber(TableNumber);
        if(BookingID == -1){
            textfield.setText("Table " + TableNumber);
        }else if(!BDAO.getTableAvailability(TableNumber, Date)){
            int AccountID = BDAO.getAccountID(BookingID);
            String EmployeeName = EDAO.getEmployee(AccountID);
            String BookingStatus = BDAO.getBookingStatus(BookingID);
            textfield.setText("Table " + TableNumber);
            textfield.appendText("\n");
            textfield.appendText(EmployeeName);
            textfield.appendText("\n");
            textfield.appendText("Booking Status: " + BookingStatus);
            textfield.appendText("\n");
            textfield.appendText("Booking ID: " + BookingID);
            textfield.appendText("\n");
            textfield.appendText("AccountID: " + AccountID);
        }
    }

}
