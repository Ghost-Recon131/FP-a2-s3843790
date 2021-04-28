package dao;
import model.BookingsModel;
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
                        RS.getString("SittingDate"), RS.getString("TableStatus"), RS.getString("Status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
