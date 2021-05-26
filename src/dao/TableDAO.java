package dao;

import model.LoginModel;
import model.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    private int NumberOfTables = 10; // prevents hard coding values in loops

    private Connection connect = SQLConnection.connect();
    private List<TableModel> listOfTables = new ArrayList<>();

    //refresh the database
    public void updateTables() {
        try {
            Statement myStmt = connect.createStatement();
            ResultSet RS = myStmt.executeQuery("select * from Tables");
            while (RS.next()) {
                listOfTables.add(new TableModel(RS.getInt("TableNumber"), RS.getInt("COVID"),
                        RS.getString("AdminMessage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // returns an int for COVID lockdown, 0 = no lockdown, 1 = partial lockdown and 2 = total lockdown
    public int getCOVID(int TableNumber) {
        int COVIDLockdown = -1;
        for (TableModel Tbm : listOfTables) {
            if (Tbm.getTableNumber() == TableNumber) {
                COVIDLockdown = Tbm.getCOVID();
            }
        }
        return COVIDLockdown;
    }

    // get the admin's message about a table
    public String getAdminMessage(int TableNumber) {
        String message = null;
        for (TableModel Tbm : listOfTables) {
            if (Tbm.getTableNumber() == TableNumber) {
                message = Tbm.getAdminMessage();
            }
        }
        return message;
    }

    // allows for setting custom messages
    // table -1 is reserved for an announcement that applies to all users & is not able to be reserved for seating
    public void UpdateAdminMessage(int TableNumber, String newMessage) {
        if(LoginModel.getCurrentUserRole().equals("admin")){
            String sql = "UPDATE Tables SET AdminMessage = ? WHERE TableNumber = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setString(1, newMessage);
                    pstmt.setInt(2, TableNumber);
                    pstmt.executeUpdate();
                    updateTables();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // helper method to lockdown tables
    private void Lockdown(int TableNumber, int LockDownLevel){
        if(LoginModel.getCurrentUserRole().equals("admin")){
            String sql = "UPDATE Tables SET TableStatus = ? , COVID = ? WHERE TableNumber = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setInt(1, 0);
                    pstmt.setInt(2, LockDownLevel);
                    pstmt.setInt(3, TableNumber);
                    pstmt.executeUpdate();
                    updateTables();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // helper method to release tables from lockdown
    private void Release(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")){
            String sql = "UPDATE Tables SET TableStatus = ? , COVID = ? WHERE TableNumber = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setInt(1, 1);
                    pstmt.setInt(2, 0);
                    pstmt.setInt(3, TableNumber);
                    pstmt.executeUpdate();
                    updateTables();
                }
                for(TableModel Tbm : listOfTables){
                    if (Tbm.getCOVID() == 0){
                        UpdateAdminMessage(-1, "No lockdowns");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // quickly locks down tables to avoid situation edge situation mentioned in README.md
    public void PartialLockdown(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 2; i <= NumberOfTables; i += 2){ //change tables 2, 4, 6, 8, 10 by for loop
                Lockdown(i, 1);
            } // DHHS is the Victorian department of health services
            Lockdown(-1, 1);
            UpdateAdminMessage(-1, "DHHS is currently mandating a maximum of 50% office capacity");
        }
    }

    // lockdown all tables
    public void LockdownAllTables(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 1; i <= NumberOfTables; i ++){ //change tables 2, 4, 6, 8, 10 by for loop
                Lockdown(i, 2);
            }
            Lockdown(-1, 2);
            UpdateAdminMessage(-1, "DHHS is currently mandating a total lockdown");
        }
    }

    // remove all COVID lockdown
    public void removeLockdown(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 1; i <= NumberOfTables; i ++){ //change tables 2, 4, 6, 8, 10 by for loop
                Release(i);
            }
            UpdateAdminMessage(-1, "COVID restrictions lifted");
        }
    }

    // lockdown specific table
    public void lockdownSpecificTable(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            Lockdown(TableNumber, 1);
            UpdateAdminMessage(TableNumber, "Table is under COVID lockdown");
        }
        Lockdown(-1, 1);
    }

    // release specific table
    public void releaseSpecificTable(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            Release(TableNumber);
            UpdateAdminMessage(TableNumber, "");
        }
    }

}
