package dao;
import model.BookingsModel;
import model.EmployeeModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsDAO {
    private Connection connect = SQLConnection.connect();
    private List<BookingsModel> listOfBookings = new ArrayList<>();

    //refresh the database
    public void updateBookings() {
        try {
            Statement myStmt = connect.createStatement();
            ResultSet RS = myStmt.executeQuery("select * from Bookings");
            while (RS.next()) {
                listOfBookings.add(new BookingsModel(RS.getInt("id"), RS.getInt("BookingID"),
                        RS.getString("BookingStatus"), RS.getInt("TableNumber"),
                        RS.getInt("PreviousTableNumber"), RS.getString("BookingDate"),
                        RS.getString("SittingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get the ID of an account via the booking ID
    public int getAccountID(int getBookingID) {
        int ID = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getBookingID() == getBookingID){
                ID = Bkm.getID();
            }
        }
        return ID;
    }

    //get the booking ID via booking ID
    public int getBookingID(int BID) {
        int bookingID = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getBookingID() == BID){
                bookingID = Bkm.getBookingID();
            }
        }
        return bookingID;
    }

}
