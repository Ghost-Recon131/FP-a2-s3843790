package model.AdminModel;

import dao.EmployeeDAO;
import model.LoginModel;

public class AdminHomeModel {
    EmployeeDAO EDAO = new EmployeeDAO();

    public String getAdminName(){
        EDAO.updateEmployee();
        return EDAO.getEmployee(LoginModel.getCurrentUserID());
    }

}
