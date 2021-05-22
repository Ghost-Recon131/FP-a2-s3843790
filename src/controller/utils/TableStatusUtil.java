package controller.utils;

import dao.TableDAO;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TableStatusUtil {
    TableDAO TDAO = new TableDAO();

    // will return status of table, true for a table that is available and false for table that is not available
    public boolean TableStatus(int TableNumber){
        TDAO.updateTables();
        return TDAO.getTableStatus(TableNumber);
    }

    public boolean COVIDLockDown(int TableNumber){
        TDAO.updateTables();
        boolean tableLockdown = true;
        int Lockdown = TDAO.getCOVID(TableNumber);
        if(Lockdown == 0){
            tableLockdown = false;
        }
        return tableLockdown;
    }

    public void SetTableColour(Rectangle rectangle, int TableNumber){
        TDAO.updateTables();
        if(TableStatus(TableNumber)){
            rectangle.setFill(javafx.scene.paint.Color.GREEN);
        }else if(COVIDLockDown(TableNumber)){
            rectangle.setFill(javafx.scene.paint.Color.ORANGE);
        }else{
            rectangle.setFill(javafx.scene.paint.Color.RED);
        }

    }

}
