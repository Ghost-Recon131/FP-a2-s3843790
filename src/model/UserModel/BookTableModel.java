package model.UserModel;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import javafx.scene.control.Label;
import model.LoginModel;

import java.time.LocalDate;

public class BookTableModel {
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();
    EmployeeDAO EDAO = new EmployeeDAO();

    public int getCurrentBookingID(){ // returns the user's booking ID
        BDAO.updateBookings();
        return BDAO.getBookingID(LoginModel.getCurrentUserID());
    }

    public boolean HasBooking(){ // checks if employee has existing booking
        boolean ActiveBooking = false;
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

    public boolean TableAvailable(int TableNumber, Label label){  // checks that 1) The table is available, 2) Table is not under COVID lockdown
        TDAO.updateTables();
        boolean Available = false;
        if(TDAO.getTableStatus(TableNumber) && TDAO.getCOVID(TableNumber) == 0){
            Available = true;
            label.setText("Table is not available for reservation");
        }
        return Available;
    }

    public boolean CorrectDate(LocalDate selectedDate, Label label) {
        TDAO.updateTables();
        boolean Correct = false;
        if(!selectedDate.isEqual(LocalDate.now()) && !selectedDate.isAfter(LocalDate.now())){
            Correct = true;
            label.setText("The selected date is available");
        }
        return Correct;
    }

    public boolean NotSameTable(int TableNumber, Label label) {
        boolean NotSameTable = false;
        EDAO.updateEmployee();
        if(EDAO.getPreviousTableNumber(LoginModel.getCurrentUserID()) == TableNumber){
            NotSameTable = true;
            label.setText("Please choose a different table as to where you sat last time");
        }
        return NotSameTable;
    }

    public void PlaceBooking(int TableNumber, LocalDate SittingDate){ //adds booking into database
        BDAO.updateBookings();
        TDAO.updateTables();
        BDAO.addBooking(LoginModel.getCurrentUserID(), TableNumber, LocalDate.now(),SittingDate);
        TDAO.setTableStatus(0, TableNumber); // set booked table as unavailable for others
    }

    public void CancelBooking(){ // sets booking as cancelled
        BDAO.updateBookings();
        TDAO.updateTables();
        BDAO.cancelBooking(getCurrentBookingID());
    }

    public String getBookingDate(){
        BDAO.updateBookings();
        return BDAO.getSittingDate(getCurrentBookingID()).toString();
    }

    public int getTableNumber(){
        BDAO.updateBookings();
        return BDAO.getTableNumber(getCurrentBookingID());
    }

}
