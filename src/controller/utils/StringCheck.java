package controller.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StringCheck {

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

}