package dao;
import java.sql.*;


public class SQLConnection {

    public static Connection connect(){
        try{
          Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:assignment.db");
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
