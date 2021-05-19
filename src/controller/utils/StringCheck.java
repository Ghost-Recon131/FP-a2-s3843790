package controller.utils;

public class StringCheck {

    public boolean VerifyString(String value){
        boolean check = false;
        if(value != null && value.length() > 0){
            check = true;
        }
        return check;
    }

}