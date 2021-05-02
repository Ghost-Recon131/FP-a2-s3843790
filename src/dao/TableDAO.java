package dao;

import model.TableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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






}
