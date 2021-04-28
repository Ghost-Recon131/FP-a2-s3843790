package model;

public class TableModel {
    private int TableNumber;
    private int TableStatus;
    private int COVID;
    private String AdminMessage;

    public TableModel(int TableNumber, int TableStatus, int COVID, String AdminMessage) {
        this.TableNumber = TableNumber;
        this.TableStatus = TableStatus;
        this.COVID = COVID;
        this.AdminMessage = AdminMessage;
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

}