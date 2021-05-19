package model;

import dao.EmployeeDAO;

import java.sql.SQLException;

public class RegisterModel {
    EmployeeDAO EDAO = new EmployeeDAO();
    //passes values onto addAccount method in Employee DAO to finalise the registration
    public boolean RegisterAccount(String Firstname, String Lastname, String Username, String Password, String SQ, String SQ_A) throws SQLException {
        EDAO.addAccount(Firstname, Lastname, Username, Password, SQ, SQ_A);

        return false;
    }
}
