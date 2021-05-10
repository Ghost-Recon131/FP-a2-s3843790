package controller.utils;

public class IntegerCheck {
    private int newInteger;

    private boolean checkInteger(String value) throws NumberFormatException{
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public int verifyInteger(String value){
        if (checkInteger(value)){
            newInteger = Integer.parseInt(value);
        }
        else {
            newInteger = -1;
        }
        return newInteger;
    }

}
