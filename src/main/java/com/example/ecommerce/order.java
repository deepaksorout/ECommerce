package com.example.ecommerce;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.SyncFailedException;
import java.sql.*;
import java.util.Calendar;

import static java.util.Calendar.*;

public class order {
    public void placeorder(String productID) throws SQLException {
        int orderid=1;
        String userID=HelloApplication.emailid;
        ResultSet res=HelloApplication.connection.executeQuery1("select max(orderID) from orders");
        if(res.next()){
            orderid=res.getInt("max(orderID)")+1;
        }
//        Date date=new Date(Calendar.getInstance().getTime().getTime());
//        System.out.println(date.toString());
        Timestamp time=new Timestamp(Calendar.getInstance().getTime().getTime());
        System.out.println(time.toString());
        String query=String.format("insert into orders values(%s,%s,'%s','%s')",orderid,productID,userID,time);
        int response=HelloApplication.connection.executeUpdate1(query);
        if(response>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Your order is placed");
            dialog.showAndWait();
        }
        else{
            System.out.println("order is not placed");
        }
    }
}
