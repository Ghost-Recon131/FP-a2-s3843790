package model;

public class BookingsModel {
    private int ID;
    private int BookingID;
    private String BookingStatus;
    private int TableNumber;
    private String BookingDate;
    private String SittingDate;

    public BookingsModel(int ID, int BookingID, String BookingStatus, int TableNumber,
                         String BookingDate, String SittingDate){
        this.ID = ID;
        this.BookingID = BookingID;
        this.BookingStatus = BookingStatus;
        this.TableNumber = TableNumber;
        this.BookingDate = BookingDate;
        this.SittingDate = SittingDate;
    }

    public int getID() {
        return ID;
    }

    public int getBookingID() {
        return BookingID;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public String getSittingDate() {
        return SittingDate;
    }
}
