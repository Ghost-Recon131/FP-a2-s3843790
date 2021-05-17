package model;

import controller.utils.SHAHashUtil;
import dao.EmployeeDAO;
import dao.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    private static int CurrentUserID;
    private static String CurrentUserRole;

    Connection connection;
    SHAHashUtil HASH = new SHAHashUtil();
    EmployeeDAO EDAO = new EmployeeDAO();

    public LoginModel(){
        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);
    }

    public Boolean isDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean isLogin(String user, String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ? and password= ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, HASH.getHash(pass));

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CurrentUserID = EDAO.getAccountIDbyUsername(user); // locates ID of current user
                CurrentUserRole = EDAO.getRole(CurrentUserID); // gets the role of current user
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e)
        {
            return false;
        }finally {
           preparedStatement.close();
           resultSet.close();
        }
    }

    //these are static so they can be used by any other class to check who the current user is and their role in the system
    public static int getCurrentUserID() {
        return CurrentUserID;
    }

    public static String getCurrentUserRole() {
        return CurrentUserRole;
    }
}
