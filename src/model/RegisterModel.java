package model;

import dao.EmployeeDAO;
import dao.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {
    private boolean IsUniqueUsername;
    private boolean IsFNameUnique;
    private boolean IsLNameUnique;

    EmployeeDAO EDAO = new EmployeeDAO();
    private Connection connect = SQLConnection.connect();
    PreparedStatement pstmt = null;
    ResultSet RS = null;

    //passes values onto addAccount method in Employee DAO to finalise the registration
    public void RegisterAccount(String Firstname, String Lastname, String Username, String Password, String SQ, String SQ_A) throws SQLException {
        EDAO.addAccount(Firstname, Lastname, Username, Password, SQ, SQ_A);
    }

    // check if username selected by user is unique
    public boolean UniqueUsername(String Username) throws SQLException{
        String query = "select * from employee where username = ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, Username);
            RS = pstmt.executeQuery();
            if (RS.next()) {
                IsUniqueUsername = false; // if username is found, the username is not unique so return false
            }
            else{
                IsUniqueUsername = true;
            }
            return IsUniqueUsername;
        }catch (SQLException e) {
            return false;
        }finally {
            pstmt.close();
            RS.close();
        }
    }

    // helper method to check for firstname
    private boolean UniqueFName(String Firstname) throws SQLException{
        String query = "select * from employee where Firstname = ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, Firstname);
            RS = pstmt.executeQuery();
            if (RS.next()) {
                IsFNameUnique = false; // if username is found, the username is not unique so return false
            }
            else{
                IsFNameUnique = true;
            }
            return IsFNameUnique;
        }catch (SQLException e) {
            return false;
        }finally {
            pstmt.close();
            RS.close();
        }
    }

    // helper method to check for lastname
    private boolean UniqueLName(String Lastname) throws SQLException{
        String query = "select * from employee where Lastname = ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, Lastname);
            RS = pstmt.executeQuery();
            if (RS.next()) {
                IsLNameUnique = false; // if username is found, the username is not unique so return false
            }
            else{
                IsLNameUnique = true;
            }
            return IsLNameUnique;
        }catch (SQLException e) {
            return false;
        }
    }

    public boolean UniqueName(String Firstname, String Lastname) throws SQLException {
        boolean isunique = false;
        if(UniqueFName(Firstname) || UniqueLName(Lastname)){
            isunique = true;
        }
        return isunique;
    }

}
