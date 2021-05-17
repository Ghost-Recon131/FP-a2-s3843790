package dao;
import controller.utils.RandValueUtil;
import model.BookingsModel;
import model.EmployeeModel;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingsDAO {
    private Connection connect = SQLConnection.connect();
    private List<BookingsModel> listOfBookings = new ArrayList<>();
    RandValueUtil RV = new RandValueUtil();

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

    //get the booking ID via user ID
    public int getBookingID(int ID) {
        int bookingID = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getID() == ID){
                bookingID = Bkm.getBookingID();
            }
        }
        return bookingID;
    }

    //get booking status via BookingID
    public String getBookingStatus(int BID){
        String BookingStatus = null;
        for (BookingsModel Bkm : listOfBookings){
            if (Bkm.getBookingID() == BID){
                BookingStatus = Bkm.getBookingStatus();
            }
        }
        return BookingStatus;
    }

    //get Table Number via BookingID
    public int getTableNumber(int BID){
        int TableNumber = -1;
        for (BookingsModel Bkm : listOfBookings){
            if (Bkm.getBookingID() == BID){
                TableNumber = Bkm.getTableNumber();
            }
        }
        return TableNumber;
    }

    //get previous Table Number via BookingID
    public int getPreviousTableNumber(int BID){
        int PreviousTableNumber = -1;
        for (BookingsModel Bkm : listOfBookings){
            if (Bkm.getBookingID() == BID){
                PreviousTableNumber = Bkm.getPreviousTableNumber();
            }
        }
        return PreviousTableNumber;
    }

    //get Booking date via BookingID
    public String getBookingDate(int BID){
        String BookingDate = null;
        for (BookingsModel Bkm : listOfBookings){
            if (Bkm.getBookingID() == BID){
                BookingDate = Bkm.getBookingDate();
            }
        }
        return BookingDate;
    }

    //get setting date via BookingID
    public String getSittingDate(int BID){
        String SittingDate = null;
        for (BookingsModel Bkm : listOfBookings){
            if (Bkm.getBookingID() == BID){
                SittingDate = Bkm.getSittingDate();
            }
        }
        return SittingDate;
    }

    //Adding a booking
    public boolean addBooking(int ID, String BookingStatus, int TableNumber, int PreviousTableNumber,
                              String Date, String SittingDate){
        boolean add = false;
        String sql = "INSERT INTO Bookings VALUES (?,?,?,?,?,?,?)";
        int BookingID = RV.randomBookingID(); //generates random ID to avoid accidentally assigning an ID that is already in use

        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, ID);
                pstmt.setString(2, BookingStatus);
                pstmt.setInt(3, TableNumber);
                pstmt.setInt(4, PreviousTableNumber);
                pstmt.setString(5, Date);
                pstmt.setString(6, SittingDate); //only admin can add another admin
                pstmt.setInt(7, BookingID);
                pstmt.executeUpdate();
                updateBookings();
            }
            for (BookingsModel Bkm : listOfBookings) {
                if (Bkm.getBookingID() == ID){
                    add = true;
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return add;
    }

    //todo finish implementing BookingsDAO
}
