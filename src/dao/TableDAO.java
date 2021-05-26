package dao;

import model.LoginModel;
import model.TableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {
    private Connection connect = SQLConnection.connect();
    private List<TableModel> listOfTables = new ArrayList<>();

    //refresh the database
    public void updateTables() {
        try {
            Statement myStmt = connect.createStatement();
            ResultSet RS = myStmt.executeQuery("select * from Tables");
            while (RS.next()) {
                listOfTables.add(new TableModel(RS.getInt("TableNumber"), RS.getInt("TableStatus"),
                        RS.getInt("COVID"), RS.getString("AdminMessage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get the status of a table via table number and returns boolean value
    public boolean getTableStatus(int TableNumber) {
        boolean TableStatus = false;
        for (TableModel Tbm : listOfTables) {
            if (Tbm.getTableNumber() == TableNumber) {
                if(Tbm.getTableStatus() == 1){
                    TableStatus = true;
                }
            }
        }
        return TableStatus;
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

    public boolean setTableStatus(int TableStatus, int TableNumber){
        boolean changed = false;
        String sql = "UPDATE Tables SET TableStatus = ? WHERE TableNumber = ?";
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);
            {
                pstmt.setInt(1, TableStatus);
                pstmt.setInt(2, TableNumber);
                pstmt.executeUpdate();
                updateTables();
            }
            changed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changed;
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
    private void Lockdown(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")){
            String sql = "UPDATE Tables SET TableStatus = ? , COVID = ? WHERE TableNumber = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setInt(1, 0);
                    pstmt.setInt(2, 1);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // quickly locks down tables to avoid situation edge situation mentioned in README.md
    public void PartialLockdown(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 2; i <= 10; i += 2){ //change tables 2, 4, 6, 8, 10 by for loop
                Lockdown(i);
            } // DHHS is the Victorian department of health services
            UpdateAdminMessage(-1, "DHHS is currently mandating a maximum of 50% office capacity");
        }
    }

    // lockdown all tables
    public void LockdownAllTables(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 1; i <= 10; i ++){ //change tables 2, 4, 6, 8, 10 by for loop
                Lockdown(i);
            }
            UpdateAdminMessage(-1, "DHHS is currently mandating a total lockdown");
        }
    }

    // remove all COVID lockdown
    public void removeLockdown(){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for(int i = 1; i <= 10; i ++){ //change tables 2, 4, 6, 8, 10 by for loop
                Release(i);
            }
            UpdateAdminMessage(-1, "COVID restrictions lifted");
        }
    }

    // lockdown specific table
    public void lockdownSpecificTable(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            Lockdown(TableNumber);
            UpdateAdminMessage(TableNumber, "Table is under COVID lockdown");
        }
    }

    // release specific table
    public void releaseSpecificTable(int TableNumber){
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            Release(TableNumber);
            UpdateAdminMessage(TableNumber, "");
        }
    }

}
