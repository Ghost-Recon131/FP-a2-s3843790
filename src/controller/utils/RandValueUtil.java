package controller.utils;

import dao.BookingsDAO;
import dao.EmployeeDAO;
import java.util.Random;

public class RandValueUtil {

    public int randomID(){
        EmployeeDAO EDAO = new EmployeeDAO();
        Random R = new Random();
        int random = R.nextInt(10000);
        int random2 = R.nextInt(10000);
        int backup = R.nextInt(10000);
        int returnvalue;

        if(random != EDAO.getAccountID(random)){ //getAccountID will return -1 if there are no matches
            returnvalue = random;
        }
        else if(random != EDAO.getAccountID(random)){
            returnvalue = backup;
        }
        else{ // in the unlikely event of a match, we will introduce another random variable
            returnvalue = random2 + backup;
        }
        return returnvalue;
    }

    public int randomBookingID(){
        BookingsDAO BDAO = new BookingsDAO();
        Random R = new Random();
        int random = R.nextInt(100000);
        int random2 = R.nextInt(10000);
        int backup = R.nextInt(100000);
        int returnvalue;

        if(random != BDAO.getBookingID(random)){ //getAccountID will return -1 if there are no matches
            returnvalue = random;
        }
        else if(random != BDAO.getBookingID(random)){
            returnvalue = backup;
        }
        else{ // in the unlikely event of a match, we will introduce another random variable
            returnvalue = random2 + backup;
        }
        return returnvalue;
    }
}
