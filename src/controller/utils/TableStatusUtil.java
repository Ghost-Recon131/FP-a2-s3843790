package controller.utils;

import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;

public class TableStatusUtil {
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();
    EmployeeDAO EDAO = new EmployeeDAO();

    // will return status of table, true for a table that is available and false for table that is not available
    public boolean TableStatus(int TableNumber, LocalDate SittingDate){
        BDAO.updateBookings();
        return BDAO.getTableAvailability(TableNumber, SittingDate);
    }

    public boolean COVIDLockDown(int TableNumber){
        TDAO.updateTables();
        boolean tableLockdown = true;
        int Lockdown = TDAO.getCOVID(TableNumber);
        if(Lockdown == 0){
            tableLockdown = false;
        }
        return tableLockdown;
    }

    public void SetTableColour(Rectangle rectangle, int TableNumber, LocalDate SittingDate){
        TDAO.updateTables();
        BDAO.updateBookings();
        if(TableStatus(TableNumber, SittingDate)){
            rectangle.setFill(javafx.scene.paint.Color.GREEN);
        }else{
            rectangle.setFill(javafx.scene.paint.Color.RED);
        }
        if(COVIDLockDown(TableNumber)) {
            rectangle.setFill(javafx.scene.paint.Color.ORANGE);
        }
    }

    public void AdminTableView(Rectangle rectangle, int TableNumber, LocalDate Date, TextArea textArea){
        BDAO.updateBookings();
        EDAO.updateEmployee();

        SetTableColour(rectangle, TableNumber, Date);

        int BookingID = BDAO.getBookingIDUsingTableNumber(TableNumber);
        if(!BDAO.getTableAvailability(TableNumber, Date)){
            int AccountID = BDAO.getAccountID(BookingID);
            String EmployeeName = EDAO.getEmployee(AccountID);
            String BookingStatus = BDAO.getBookingStatus(BookingID);
            textArea.setText("Table " + TableNumber + "\n" + EmployeeName + "\n" + "Booking Status: " + BookingStatus +
                    "\n" + "Booking ID: " + BookingID + "\n" + "AccountID: " + AccountID);
        }else{
            textArea.setText("Table " + TableNumber);
        }
    }

}
