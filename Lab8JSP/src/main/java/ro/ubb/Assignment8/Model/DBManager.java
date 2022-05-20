package ro.ubb.Assignment8.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    private Statement statement;
    private static Connection connection;

    public DBManager(){
        connect();
    }

    public void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/forum";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "");
            statement = connection.createStatement();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    
}
