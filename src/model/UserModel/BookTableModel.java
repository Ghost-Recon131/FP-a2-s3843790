package model.UserModel;
import dao.BookingsDAO;
import dao.EmployeeDAO;
import dao.TableDAO;
import javafx.scene.control.Label;
import model.LoginModel;

import java.time.LocalDate;

public class BookTableModel { //todo: book tables on days, not to set table as unavailable for all days
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
            EDAO.updateEmployee();
            int BookingID = BDAO.getBookingID(LoginModel.getCurrentUserID());
            int LastTable = EDAO.getPreviousTableNumber(LoginModel.getCurrentUserID());
            boolean Status1 = BDAO.getBookingStatus(BookingID).equals("cancelled");
            boolean Status2 = BDAO.getBookingStatus(BookingID).equals("completed");
            if(LastTable != -1 || !Status1 || !Status2){
                ActiveBooking = true;
            }
            return ActiveBooking;
        }catch(Exception e){
            return ActiveBooking;
        }
    }

    public boolean TableAvailable(int TableNumber, LocalDate SelectedDate,Label label){  // checks that 1) The table is available, 2) Table is not under COVID lockdown
        BDAO.updateBookings();
        TDAO.updateTables();
        boolean Available = false;
        if(BDAO.getTableAvailability(TableNumber, SelectedDate) && TDAO.getCOVID(TableNumber) == 0){
            Available = true;
        }else{
            label.setText("Table is not available for reservation");
        }
        return Available;
    }

    public boolean CorrectDate(LocalDate selectedDate, Label label) {
        boolean Correct = false;
        if(selectedDate.isAfter(LocalDate.now())){
            Correct = true;
        }else{
            label.setText("The selected date is not available");
        }
        return Correct;
    }

    public boolean NotSameTable(int TableNumber, Label label) {
        boolean NotSameTable = false;
        EDAO.updateEmployee();
        if(EDAO.getPreviousTableNumber(LoginModel.getCurrentUserID()) != TableNumber){
            NotSameTable = true;
        }else{
            label.setText("Please choose a different table as to where you sat last time");
        }
        return NotSameTable;
    }

    public void PlaceBooking(int TableNumber, LocalDate SittingDate){ //adds booking into database
        BDAO.updateBookings();
        BDAO.addBooking(LoginModel.getCurrentUserID(), TableNumber, LocalDate.now(),SittingDate);
    }

    public boolean CanCancelBooking(){
        boolean CanCancel = true;
        BDAO.updateBookings();
        LocalDate SittingDate = BDAO.getSittingDate(getCurrentBookingID()); // gets current reserved date
        LocalDate CurrentDate = LocalDate.now(); // gets today's date
        LocalDate CutoffDate = SittingDate.minusDays(2); // gets the date 2 days before the reserved date
        if(SittingDate.isEqual(CurrentDate) || SittingDate.isBefore(CutoffDate)){
            CanCancel = false;
        }
        return CanCancel;
    }

    public void CancelBooking(){ // sets booking as cancelled //todo implement cancel booking
        BDAO.updateBookings();
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

    public boolean BookingIsApproved(){
        return BDAO.getBookingStatus(getCurrentBookingID()).equals("approved");
    }

    public boolean SittingDateIsNow(){
        return BDAO.getSittingDate(getCurrentBookingID()).isEqual(LocalDate.now());
    }

}
