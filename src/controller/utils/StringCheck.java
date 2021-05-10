package controller.utils;

public class StringCheck {

    public String VerifyString(String value){
        String returnValue;
        if(value != null && value.length() > 0){
            returnValue = value;
        }
        else{
            returnValue = "Invalid entry";
        }
        return returnValue;
    }

}