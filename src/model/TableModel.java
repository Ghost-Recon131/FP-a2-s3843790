package model;

public class TableModel {
    private int TableNumber;
    private int TableStatus;
    private int COVID;
    private String AdminMessage;
    private String SittingDate;

    public TableModel(int TableNumber, int TableStatus, int COVID, String AdminMessage, String SittingDate) {
        this.TableNumber = TableNumber;
        this.TableStatus = TableStatus;
        this.COVID = COVID;
        this.AdminMessage = AdminMessage;
        this.SittingDate = SittingDate;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public int getTableStatus() {
        return TableStatus;
    }

    public int getCOVID() {
        return COVID;
    }

    public String getAdminMessage() {
        return AdminMessage;
    }

    public String getSittingDate() {
        return SittingDate;
    }
}