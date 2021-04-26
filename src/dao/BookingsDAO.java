package dao;
import model.EmployeeModel;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingsDAO {
    private Connection connect = SQLConnection.connect();
    private List<EmployeeModel> listOfEmployees = new ArrayList<>();


}
