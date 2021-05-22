package model;

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
    PreparedStatement pstmt = null;
    ResultSet RS = null;

    private String NewPassword;
    SHAHashUtil HASH = new SHAHashUtil();
    RandPasswordUtil RPU = new RandPasswordUtil();
    EmployeeDAO EDAO = new EmployeeDAO();

    public boolean LocateUser(String SQ, String SQ_A) throws SQLException {
        boolean Located;
        String query = "select * from employee where Secret_Question = ? and SQ_Answer = ?";

        try {
            pstmt = connect.prepareStatement(query);
            pstmt.setString(1, SQ);
            pstmt.setString(2, HASH.getHash(SQ_A));
            RS = pstmt.executeQuery();
            if (RS.next()) {
                Located = true;
            }else{
                Located = false;
            }
        } catch (SQLException e){
            return false;
        }finally {
            pstmt.close();
            RS.close();
        }
        return Located;
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

    public boolean InactiveAccount(){
        boolean inactiveAccount = true;
        EDAO.updateEmployee();
        inactiveAccount = !EDAO.getStatus(LoginModel.getCurrentUserID()).equals("active");
        return inactiveAccount;
    }

}
