package dao;
import controller.utils.RandValueUtil;
import model.BookingsModel;
import model.LoginModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
                        RS.getString("BookingDate"), RS.getString("SittingDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get the ID of an account via the booking ID
    public int getAccountID(int getBookingID) {
        int ID = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getBookingID() == getBookingID) {
                ID = Bkm.getID();
            }
        }
        return ID;
    }

    //get the booking ID via user ID
    public int getBookingID(int ID) {
        int bookingID = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getID() == ID) {
                bookingID = Bkm.getBookingID();
            }
        }
        return bookingID;
    }

    //get booking status via BookingID
    public String getBookingStatus(int BID) {
        String BookingStatus = null;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getBookingID() == BID) {
                BookingStatus = Bkm.getBookingStatus();
            }
        }
        return BookingStatus;
    }

    //get Table Number via BookingID
    public int getTableNumber(int BID) {
        int TableNumber = -1;
        for (BookingsModel Bkm : listOfBookings) {
            if (Bkm.getBookingID() == BID) {
                TableNumber = Bkm.getTableNumber();
            }
        }
        return TableNumber;
    }

    //get Booking date via BookingID
    public LocalDate getBookingDate(int BID) {
        LocalDate BookingDate = null;
        try{
            for (BookingsModel Bkm : listOfBookings) {
                if (Bkm.getBookingID() == BID) {
                    BookingDate = LocalDate.parse(Bkm.getBookingDate()); // convert string into LocalDate format
                }
            }
        }catch(DateTimeParseException e){
            System.err.println("Error in converting string into LocalDate format");
        }
        return BookingDate;
    }

    //get setting date via BookingID
    public LocalDate getSittingDate(int BID) {
        LocalDate SittingDate = null;
        try{
            for (BookingsModel Bkm : listOfBookings) {
                if (Bkm.getBookingID() == BID) {
                    SittingDate = LocalDate.parse(Bkm.getSittingDate()); // convert string into LocalDate format
                }
            }
        }catch(DateTimeParseException e){
            System.err.println("Error in converting string into LocalDate format");
        }
        return SittingDate;
    }

    //Adding a booking
    public boolean addBooking(int ID, int TableNumber, LocalDate BookingDate, LocalDate SittingDate) {
        boolean add = false;
        String sql = "INSERT INTO Bookings VALUES (?,?,?,?,?,?)";
        int BookingID = RV.randomBookingID(); //generates random ID to avoid accidentally assigning an ID that is already in use

        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            {
                pstmt.setInt(1, ID);
                pstmt.setString(2, "pending");
                pstmt.setInt(3, TableNumber);
                pstmt.setString(4, BookingDate.toString());
                pstmt.setString(5, SittingDate.toString()); //only admin can add another admin
                pstmt.setInt(6, BookingID);
                pstmt.executeUpdate();
                updateBookings();
            }
            for (BookingsModel Bkm : listOfBookings) {
                if (Bkm.getBookingID() == ID) {
                    add = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return add;
    }

    public void cancelBooking(int BookingID) {
        String sql = "UPDATE Bookings SET BookingStatus = ? WHERE BookingID = ?";
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            {
                pstmt.setString(1, "cancelled");
                pstmt.setInt(2, BookingID);
                pstmt.executeUpdate();
                updateBookings();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean approveBooking(int BookingID) {
        boolean approve = false;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "UPDATE Bookings SET BookingStatus = ? WHERE BookingID = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setString(1, "approved");
                    pstmt.executeUpdate();
                    updateBookings();
                }
                for (BookingsModel Bkm : listOfBookings) {
                    if (Bkm.getBookingID() == BookingID) {
                        approve = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return approve;
    }

    public boolean rejectBooking(int BookingID) {
        boolean reject = false;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "UPDATE Bookings SET BookingStatus = ? WHERE BookingID = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setString(1, "rejected");
                    pstmt.executeUpdate();
                    updateBookings();
                }
                for (BookingsModel Bkm : listOfBookings) {
                    if (Bkm.getBookingID() == BookingID) {
                        reject = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reject;
    }

}