package dao;

import controller.utils.RandValueUtil;
import model.EmployeeModel;
import controller.utils.SHAHashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends EmployeeDAO{
    private Connection connect = SQLConnection.connect();
    private List<EmployeeModel> listOfEmployees = new ArrayList<>();
    SHAHashUtil HASH = new SHAHashUtil();
    RandValueUtil RV = new RandValueUtil();

    //delete an account using the account's id
    public boolean deleteAccount(int id){
        boolean result = true;
        String sql = "DELETE FROM Employee WHERE id = ?";
        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                updateEmployee();
            }
            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getID() == id){
                    result = false;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //allows admin to update an account
    public boolean updateAccount (int CurrentID, int id, String Firstname, String Lastname, String Username, String Password,
                                  String Secret_Question, String SQ_Answer){
        boolean result = false;
        String sql = "UPDATE Employee SET id = ?, Firstname = ?, Lastname = ?, Username = ?, Password = ?, " +
                "Secret_Question = ?, SQ_Answer = ? WHERE id = ?";
        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, id);
                pstmt.setString(2, Firstname);
                pstmt.setString(3, Lastname);
                pstmt.setString(4, Username);
                pstmt.setString(5, HASH.getHash(Password));
                pstmt.setString(6, Secret_Question);
                pstmt.setString(7, HASH.getHash(SQ_Answer));
                pstmt.setInt(8, CurrentID);
                pstmt.executeUpdate();
            }
            for(EmployeeModel Emp : listOfEmployees){ //check the account ID is valid
                if(Emp.getID() == id){
                    result = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //Adding an admin account
    public boolean addAdmin(String Firstname, String Lastname, String Username, String Password,
                              String Secret_Question, String SQ_Answer){
        boolean add = false;
        String sql = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?)";
        int id = RV.randomID(); //generates random ID to avoid accidentally assigning an ID that is already in use

        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, id);
                pstmt.setString(2, Firstname);
                pstmt.setString(3, Lastname);
                pstmt.setString(4, Username);
                pstmt.setString(5, HASH.getHash(Password));
                pstmt.setString(6, "admin");
                pstmt.setString(7, Secret_Question);
                pstmt.setString(8, HASH.getHash(SQ_Answer));
                pstmt.executeUpdate();
                updateEmployee();
            }
            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getID() == id){
                    add = true;
                    break;
                }
            }
        }catch (SQLException | NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return add;
    }





}
