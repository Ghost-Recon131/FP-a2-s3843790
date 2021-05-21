package dao;

import model.BookingsModel;
import model.EmployeeModel;
import controller.utils.RandValueUtil;
import controller.utils.SHAHashUtil;
import model.LoginModel;

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
                        RS.getString("Secret_Question"), RS.getString("SQ_Answer"),
                        RS.getString("Status"), RS.getInt("PreviousTableNumber")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get the ID of an account using the account's username
    public int getAccountID(int id) {
        int ID = -1;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == id){
                ID = Emp.getID();
            }
        }
        return ID;
    }

    public int getAccountIDbyUsername(String Username) {
        int ID = -1;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getUsername().equals(Username)){
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

    //get previous Table Number via ID
    public int getPreviousTableNumber(int ID) {
        int PreviousTableNumber = -1;
        for (EmployeeModel Emp : listOfEmployees) {
            if (Emp.getID() == ID) {
                PreviousTableNumber = Emp.getPreviousTableNumber();
            }
        }
        return PreviousTableNumber;
    }

    //Adding an account / Register
    public boolean addAccount(String Firstname, String Lastname, String Username, String Password,
                              String Secret_Question, String SQ_Answer){
        boolean add = false;
        String sql = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?)";
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
                pstmt.setInt(10, -1);// default table which does not effect initial booking
                pstmt.executeUpdate();
                updateEmployee();
            }

            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getID() == id){
                    add = true;
                    break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return add;
    }

    // allow employees to change their password
    public boolean changePassword(int id, String newPassword) {
        boolean Change = false;
        String HashedPassword = HASH.getHash(newPassword);
        String sql = "UPDATE Employee SET Password = \"" + HashedPassword + "\" WHERE id = \"" + id + "\"";
        try{
            Statement myStmt = connect.createStatement();
            myStmt.executeUpdate(sql);
            updateEmployee();

            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getID() == id && Emp.getPassword().equals(HashedPassword)){
                    Change = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Change;
    }

    public boolean resetPassword(String SQ_A, String newPassword) throws NoSuchAlgorithmException {
        boolean Reset = false;
        String sql = "UPDATE Employee SET Password = ? WHERE SQ_Answer = ?";
        try{
            PreparedStatement pstmt = connect.prepareStatement(sql);{
                pstmt.setString(1, HASH.getHash(newPassword));
                pstmt.setString(2, HASH.getHash(SQ_A));
                pstmt.executeUpdate();
                updateEmployee();
            }

            for(EmployeeModel Emp : listOfEmployees){
                if(Emp.getSQAnswer().equals(HASH.getHash(SQ_A)) && Emp.getPassword().equals(HASH.getHash(newPassword))){
                    Reset = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Reset;
    }

    // Below are administrator functions
    //delete an account using the account's id
    public boolean deleteAccount(int id){
        boolean result = true;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "DELETE FROM Employee WHERE id = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                    updateEmployee();
                }
                for (EmployeeModel Emp : listOfEmployees) {
                    if (Emp.getID() == id) {
                        result = false;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //allows admin to update an account
    public boolean updateAccount (int CurrentID, int id, String Firstname, String Lastname, String Username, String Password,
                                  String Secret_Question, String SQ_Answer){
        boolean result = false;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "UPDATE Employee SET id = ?, Firstname = ?, Lastname = ?, Username = ?, Password = ?, " +
                    "Secret_Question = ?, SQ_Answer = ? WHERE id = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
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
                for (EmployeeModel Emp : listOfEmployees) { //check the account ID is valid
                    if (Emp.getID() == id) {
                        result = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Adding an admin account
    public boolean addAdmin(String Firstname, String Lastname, String Username, String Password,
                            String Secret_Question, String SQ_Answer){
        boolean add = false;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?)";
            int id = RV.randomID(); //generates random ID to avoid accidentally assigning an ID that is already in use

            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setInt(1, id);
                    pstmt.setString(2, Firstname);
                    pstmt.setString(3, Lastname);
                    pstmt.setString(4, Username);
                    pstmt.setString(5, HASH.getHash(Password));
                    pstmt.setString(6, "admin");
                    pstmt.setString(7, Secret_Question);
                    pstmt.setString(8, HASH.getHash(SQ_Answer));
                    pstmt.setString(9, "active");
                    pstmt.executeUpdate();
                    updateEmployee();
                }
                for (EmployeeModel Emp : listOfEmployees) {
                    if (Emp.getID() == id) {
                        add = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return add;
    }

    // output a list of employee accounts
    public List<EmployeeModel> getListOfEmployees() {
        List<EmployeeModel> Employees = new ArrayList<>();
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for (EmployeeModel Emp : listOfEmployees) {
                if (Emp.getRole().equals("employee")) {
                    Employees.add(Emp);
                }
            }
        }
        return Employees;
    }

    // outputs a list of admin accounts
    public List<EmployeeModel> getListOfAdmins() {
        List<EmployeeModel> Admins = new ArrayList<>();
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            for (EmployeeModel Emp : listOfEmployees) {
                if (Emp.getRole().equals("admin")) {
                    Admins.add(Emp);
                }
            }
        }
        return Admins;
    }

    //allows admin to activate or deactivate an account, controller will set the status = active or deactivated
    public boolean activateAccount(int id, String newStatus){
        boolean activate = false;
        if(LoginModel.getCurrentUserRole().equals("admin")) {
            String sql = "UPDATE Employee SET Status = ? WHERE id = ?";
            try {
                PreparedStatement pstmt = connect.prepareStatement(sql);
                {
                    pstmt.setString(1, newStatus);
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();
                    updateEmployee();
                }
                for (EmployeeModel Emp : listOfEmployees) {
                    if (Emp.getID() == id && Emp.getStatus().equals(newStatus)) {
                        activate = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return activate;
    }

}
