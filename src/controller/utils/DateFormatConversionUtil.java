package controller.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateFormatConversionUtil {

    // converts a supplied String to LocalDate format, allowing for easy date calculations
    public LocalDate FormatToLocalDate(String ToFormat){
        LocalDate FormattedDate = null;
        try{
            FormattedDate = LocalDate.parse(ToFormat);
        }catch(DateTimeParseException e){
            System.err.println("Error in converting to LocalDate in DateFormatConversionUtil");
        }
        return FormattedDate;
    }

}
