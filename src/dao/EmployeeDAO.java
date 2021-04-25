package dao;

import model.EmployeeModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String role = null;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id) {
                role = Emp.getSecretQuestion();
            }
        }
        return role;
    }








}
