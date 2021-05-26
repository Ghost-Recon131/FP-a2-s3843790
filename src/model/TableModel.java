package model;

public class TableModel {
    private int TableNumber;
    private int COVID;
    private String AdminMessage;

    public TableModel(int TableNumber, int COVID, String AdminMessage) {
        this.TableNumber = TableNumber;
        this.COVID = COVID;
        this.AdminMessage = AdminMessage;
    }

    public int getTableNumber() {
        return TableNumber;
    }

    public int getCOVID() {
        return COVID;
    }

    public String getAdminMessage() {
        return AdminMessage;
    }

}