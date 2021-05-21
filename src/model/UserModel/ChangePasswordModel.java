package model.UserModel;

import dao.EmployeeDAO;
import model.LoginModel;

public class ChangePasswordModel {
    EmployeeDAO EDAO = new EmployeeDAO();

    public void ChangePassword(String newPassword){
        try{
            EDAO.updateEmployee();
            EDAO.changePassword(LoginModel.getCurrentUserID(), newPassword);
        }catch(Exception e){
            System.err.println("Error in employee changing their own password");
        }
    }

}
