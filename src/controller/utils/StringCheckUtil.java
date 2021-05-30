package controller.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StringCheckUtil {

    public boolean VerifyString(String value){
        return value != null && value.length() > 0;
    }

    // sets error messages when information is missing
    public boolean InputNotEmpty(TextField Variable, Label ErrorVariable){
        boolean Error = true;
        if(!VerifyString(Variable.getText())){
            ErrorVariable.setText("This cannot be empty");
        }
        else{
            Error = false;
        }
        return Error;
    }

    public boolean StringInputNotEmpty(String Variable, Label ErrorVariable){
        boolean Error = true;
        if(!VerifyString(Variable)){
            ErrorVariable.setText("This cannot be empty");
        }
        else{
            Error = false;
        }
        return Error;
    }

}