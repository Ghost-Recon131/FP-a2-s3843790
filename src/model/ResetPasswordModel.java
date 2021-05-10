package model;

import controller.utils.SHAHashUtil;
import dao.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPasswordModel {
    private Connection connect = SQLConnection.connect();
    SHAHashUtil HASH = new SHAHashUtil();

    public Boolean isLogin(String SQ, String SQ_A) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where Secret_Question = ? and SQ_Answer= ?";
        try {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, SQ);
            preparedStatement.setString(2, HASH.getHash(SQ_A));
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
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

}
