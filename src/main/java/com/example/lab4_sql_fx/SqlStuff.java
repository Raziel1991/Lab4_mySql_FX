package com.example.lab4_sql_fx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlStuff {


    private static Connection con;

    //Starts the DB Connection
    public static Connection startConnection(String URL, String USER, String PASS) {

        con = null;
        try {
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Connection Established");

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Connection Failed");
        }


        return con;
    }

    //Ends the DB connection
    public static void closeConnection(){
        if(con != null){
            try {
                con.close();
                System.out.println("Connection Closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
