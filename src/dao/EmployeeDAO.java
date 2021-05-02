package dao;

import model.EmployeeModel;
import controller.utils.RandValueUtil;
import controller.utils.SHAHashUtil;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection connect = SQLConnection.connect();
    private List<EmployeeModel> listOfEmployees = new ArrayList<>();
    SHAHashUtil HASH = new SHAHashUtil();
    RandValueUtil RV = new RandValueUtil();

    //refresh the database
    public void updateEmployee() {
        try {
            Statement myStmt = connect.createStatement();
            ResultSet RS = myStmt.executeQuery("select * from Employee");
            while (RS.next()) {
                listOfEmployees.add(new EmployeeModel(RS.getInt("id"), RS.getString("Firstname"),
                        RS.getString("Lastname"), RS.getString("Username"),
                        RS.getString("Password"), RS.getString("Role"),
                        RS.getString("Secret_Question"), RS.getString("SQ_Answer"), RS.getString("Status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get the ID of an account
    public int getAccountID(int id) {
        int ID = -1;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id){
                ID = Emp.getID();
            }
        }
        return ID;
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
                password = Emp.getPassword();
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
                SQA = Emp.getSQAnswer();
            }
        }
        return SQA;
    }

    //get status of an account
    public String getStatus(int id) {
        String Status = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                Status = Emp.getStatus();
            }
        }
        return Status;
    }

    //Adding an account
    public boolean addAccount(String Firstname, String Lastname, String Username, String Password,
                              String Secret_Question, String SQ_Answer){
        boolean add = false;
        String sql = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)";
        int id = RV.randomID(); //generates random ID to avoid accidentally assigning an ID that is already in use

        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setInt(1, id);
                pstmt.setString(2, Firstname);
                pstmt.setString(3, Lastname);
                pstmt.setString(4, Username);
                pstmt.setString(5, HASH.getHash(Password));
                pstmt.setString(6, "employee"); //only admin can add another admin
                pstmt.setString(7, Secret_Question);
                pstmt.setString(8, HASH.getHash(SQ_Answer));
                pstmt.setString(9, "active");
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

    // allow employees to change their password
    public boolean changePassword(int id, String newPassword){
        boolean Change = false;
        String sql = "UPDATE Employee SET Password = \"" + newPassword + "\" WHERE id = \"" + id + "\"";
        try{
            Statement myStmt = connect.createStatement();
            myStmt.executeUpdate(sql);
            updateEmployee();

            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getID() == id && Emp.getPassword().equals(newPassword)){
                    Change = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Change;
    }

}
