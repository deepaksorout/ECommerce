package com.example.ecommerce;
import java.sql.*;

public class DatabaseConnection {
    Connection con =null;
    String url="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String username="root";
    String password="45516111";
    DatabaseConnection() throws SQLException {
        con= DriverManager.getConnection(url,username,password);

        if(con!=null){
           // System.out.println("connection successful");
        }
    }

    public ResultSet executeQuery1(String query) {
        ResultSet result=null;
        try{
            Statement statement=con.createStatement();
            result = statement.executeQuery(query);
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return result;
    }
    public int executeUpdate1(String query){
        int row=0;
        try{
            Statement statement=con.createStatement();
            row=statement.executeUpdate(query);
        }
        catch(Exception e){
            e.getStackTrace();
        }
        return row;
    }
}
