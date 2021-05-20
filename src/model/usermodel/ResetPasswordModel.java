package model.usermodel;

import controller.utils.RandPasswordUtil;
import controller.utils.SHAHashUtil;
import dao.EmployeeDAO;
import dao.SQLConnection;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPasswordModel {
    private Connection connect = SQLConnection.connect();
    private String NewPassword;
    SHAHashUtil HASH = new SHAHashUtil();
    RandPasswordUtil RPU = new RandPasswordUtil();
    EmployeeDAO EDAO = new EmployeeDAO();

    public Boolean LocateUser(String SQ, String SQ_A) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet resultSet=null;
        String query = "select * from employee where Secret_Question = ? and SQ_Answer = ?";
        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, SQ);
            pstmt.setString(2, HASH.getHash(SQ_A));
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }finally {
            pstmt.close();
            resultSet.close();
        }
    }

    public boolean ResetPassword(String SQ_A) {
        try {
            NewPassword = RPU.getRandomPassword();
            EDAO.resetPassword(SQ_A, NewPassword);
        }catch(NoSuchAlgorithmException e){
            System.err.println("Exception in writing new password to database");
        }
        return true;
    }

    public String getNewPassword() {
        return NewPassword;
    }

}
