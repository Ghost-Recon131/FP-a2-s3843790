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
            ResultSet RS = myStmt.executeQuery("select * from Bookings");
            while (RS.next()) {
                listOfTables.add(new TableModel(RS.getInt("TableNumber"), RS.getInt("TableStatus"),
                        RS.getInt("COVID"), RS.getString("AdminMessage")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //todo complete TableDAO
    //get the status of a table via table number
    public int getTableStatus(int TableNumber) {
        int TableStatus = -1;
        for (TableModel Tbm : listOfTables) {
            if (Tbm.getTableNumber() == TableNumber) {
                TableStatus = Tbm.getTableStatus();
            }
        }
        return TableStatus;
    }

    public boolean getCOVID(int TableNumber) {
        boolean COVIDLockdown = true;
        for (TableModel Tbm : listOfTables) {
            if (Tbm.getTableNumber() == TableNumber) {
                if(Tbm.getCOVID() == 0){
                    COVIDLockdown = false;
                }
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
    //todo finish admin setters
    // allows for setting custom messages
    // table -1 is reserved for an announcement that applies to all users & is not able to be reserved for seating
    public boolean UpdateAdminMessage(String newMessage, int TableNumber) {
        boolean update = false;
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
                update = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update;
    }

}
