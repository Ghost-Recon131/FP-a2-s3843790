package model.UserModel;

import controller.utils.DateFormatConversionUtil;
import dao.BookingsDAO;
import dao.TableDAO;
import model.LoginModel;

public class BookTableModel {
    TableDAO TDAO = new TableDAO();
    BookingsDAO BDAO = new BookingsDAO();
    DateFormatConversionUtil DFU = new DateFormatConversionUtil();

    private int getCurrentBookingID(){ // returns the user's booking ID
        BDAO.updateBookings();
        return BDAO.getBookingID(LoginModel.getCurrentUserID());
    }



}
