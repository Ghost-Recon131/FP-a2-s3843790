package model;

public class BookingsModel {
    // https://www.geeksforgeeks.org/date-class-java-examples/ use for implementing date features
    private int ID;
    private String Role;
    private int TableNumber;
    private int PreviousTableNumber;
    private String Date;
    private String SittingDate;
    private String TableStatus;

    public BookingsModel(int ID, String Role, int TableNumber, int PreviousTableNumber, String Date, String SittingDate,
                         String TableStatus){
        this.ID = ID;
        this.Role = Role;
        this.TableNumber = TableNumber;
        this.PreviousTableNumber = PreviousTableNumber;
        this.Date = Date;
        this.SittingDate = SittingDate;
    }

}
