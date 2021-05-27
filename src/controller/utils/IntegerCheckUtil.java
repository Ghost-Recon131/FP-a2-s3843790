package controller.utils;

public class IntegerCheckUtil {
    private int newInteger;

    private boolean checkInteger(String value) throws NumberFormatException{
        try {
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int verifyInteger(String value){
        try{
            if (checkInteger(value)){
                newInteger = Integer.parseInt(value);
            }
            else {
                newInteger = -1;
            }
        }catch (Exception e){
            return newInteger;
        }
        return newInteger;
    }

}
