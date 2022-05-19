package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
A factory... is a class that cannot be instantiated, but contains a static method
that returns a new connection every time it's called

A singleton... is a class that also cannot be instantiated, but contains a static method
that returns a single static connection when its method is called
 */

public class ConnectionFactory {
    private ConnectionFactory(){

    }
    public static Connection getConnection(){
        /*
        storing the password as plain text within code is a bad idea...
        because then people could just steal our credentials
        instead: we can use a file on our computer that's read and keep it secure that way
         */
        try {
            FileInputStream propertiesInput = new FileInputStream("src/main/resources/sql.properties");
//            reading in properties as key value pairs
            Properties props= new Properties();
            props.load(propertiesInput);

            String url = (String) props.get("url");
            String username = (String) props.get("username");
            String password = (String) props.get("password");

            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
