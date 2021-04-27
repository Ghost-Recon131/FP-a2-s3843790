package model;

public class BookingsModel {
    private int ID;
    private int BookingID;
    private String BookingStatus;
    private int TableNumber;
    private int PreviousTableNumber;
    private String Date;
    private String SittingDate;
    private String TableStatus;

    public BookingsModel(int ID, int BookingID, String BookingStatus, int TableNumber, int PreviousTableNumber, String Date, String SittingDate,
                         String TableStatus){
        this.ID = ID;
        this.BookingID = BookingID;
        this.BookingStatus = BookingStatus;
        this.TableNumber = TableNumber;
        this.PreviousTableNumber = PreviousTableNumber;
        this.Date = Date;
        this.SittingDate = SittingDate;
        this.TableStatus = TableStatus;
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

    public int getPreviousTableNumber() {
        return PreviousTableNumber;
    }

    public String getDate() {
        return Date;
    }

    public String getSittingDate() {
        return SittingDate;
    }

    public String getTableStatus() {
        return TableStatus;
    }
}
