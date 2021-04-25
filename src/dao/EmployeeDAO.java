package dao;

import model.EmployeeModel;
import model.SHAHash;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connect = SQLConnection.connect();
    private List<EmployeeModel> listOfEmployees = new ArrayList<>();

    //refresh the database
    public void updateEmployee() {
        try {
            Statement myStmt = connect.createStatement();
            ResultSet RS = myStmt.executeQuery("select * from Employee");
            while (RS.next()) {
                listOfEmployees.add(new EmployeeModel(RS.getInt("id"), RS.getString("Firstname"),
                        RS.getString("Lastname"), RS.getString("Username"),
                        RS.getString("Password"), RS.getString("Role"),
                        RS.getString("Secret_Question"), RS.getString("SQ_Answer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get the full name of an account's owner using their ID
    public String getEmployee(int id) {
        String name = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id){
                name = Emp.getFullName();
            }
        }
        return name;
    }

    //Get the username of an account's owner their ID
    public String getUsername(int id) {
        String username = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                username = Emp.getUsername();
            }
        }
        return username;
    }

    //Get the hashed password of an account's owner using their ID
    public String getPassword(int id) {
        String password = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                password = Emp.getHashedPassword();
            }
        }
        return password;
    }

    //get Role of an account's owner using their ID
    public String getRole(int id) {
        String role = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                role = Emp.getRole();
            }
        }
        return role;
    }

    //get Secret Question of an account
    public String getSecretQuestion(int id) {
        String SQ = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                SQ = Emp.getSecretQuestion();
            }
        }
        return SQ;
    }

    //get answer to Secret Question of an account
    public String getSecretQuestionAnswer(int id) {
        String SQA = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                SQA = Emp.getSecretQuestion();
            }
        }
        return SQA;
    }

    //Adding an account
    public boolean addAccount(int id, String Firstname, String Lastname, String Username, String Password, String Role,
                              String Secret_Question, String SQ_Answer){
        boolean add = false;
        SHAHash HASH = new SHAHash();
        String sql = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, id);
                pstmt.setString(2, Firstname);
                pstmt.setString(3, Lastname);
                pstmt.setString(4, Username);
                pstmt.setString(5, HASH.getHash(Password));
                pstmt.setString(6, Role);
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

    //TODO implement updating of account information
    public boolean updateAccount (int id, String Firstname, String Lastname, String Username, String Password, String Role,
                                  String Secret_Question, String SQ_Answer){
        boolean result = false;
        String sql = "UPDATE Employee SET ";



        return result;
    }




}