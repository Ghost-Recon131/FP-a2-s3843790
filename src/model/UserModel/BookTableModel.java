package model.UserModel;

import controller.utils.DateFormatConversionUtil;
import dao.BookingsDAO;
import dao.TableDAO;
import model.LoginModel;

import java.time.LocalDate;

public class BookTableModel {
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();
    DateFormatConversionUtil DFU = new DateFormatConversionUtil();

    private int getCurrentBookingID(){ // returns the user's booking ID
        BDAO.updateBookings();
        return BDAO.getBookingID(LoginModel.getCurrentUserID());
    }

    public boolean HasBooking(){
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

    // checks that 1) The table is available, 2) Table is not under COVID lockdown
    public boolean TableAvailable(int TableNumber){
        TDAO.updateTables();
        return TDAO.getTableStatus(TableNumber) && TDAO.getCOVID(TableNumber) == 0;
    }

    public boolean CorrectDate(LocalDate selectedDate) {
        return !selectedDate.isEqual(LocalDate.now()) && !selectedDate.isAfter(LocalDate.now());
    }

}
