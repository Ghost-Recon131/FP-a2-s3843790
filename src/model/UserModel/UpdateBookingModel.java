package model.UserModel;

import controller.utils.DateFormatConversionUtil;
import dao.BookingsDAO;
import dao.TableDAO;
import model.LoginModel;

import java.time.LocalDate;

public class UpdateBookingModel {
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();
    DateFormatConversionUtil DFU = new DateFormatConversionUtil();

    private int getCurrentBookingID(){ // returns the user's booking ID
        BDAO.updateBookings();
        return BDAO.getBookingID(LoginModel.getCurrentUserID());
    }

    // checks that the request to change a booking is more than 2 days before the sitting date of the booking
    public boolean CanChangeBooking(){
        TDAO.updateTables();
        BDAO.updateBookings();
        boolean ChangeBooking = false;
        LocalDate SittingDate = BDAO.getSittingDate(getCurrentBookingID());

        LocalDate CurrentDate =  LocalDate.now();
        if(SittingDate.isBefore(CurrentDate.minusDays(2))){
            ChangeBooking = true;
        }
        return ChangeBooking;
    }

    // this is used to pass values onto Bookings DAO to change the booking
    public void ChangeBooking(int TableNumber, String BookingDate, String SittingDate, int BookingID){
        BDAO.updateBookings();
        LocalDate FormattedBookingDate = DFU.FormatToLocalDate(BookingDate);
        LocalDate FormattedSittingDate = DFU.FormatToLocalDate(SittingDate);
        BDAO.changeBooking(TableNumber, FormattedBookingDate, FormattedSittingDate, BookingID);
    }

}
