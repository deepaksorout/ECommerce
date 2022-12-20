package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerPageController {
    @FXML
    TextField productName;
    @FXML
    TextField price;
    @FXML
    TextField sellerID;

    @FXML
    public void AddProduct(MouseEvent event) throws SQLException {
        int productid=1;
        String tempquery="select max(productID) from product";
        ResultSet temp=HelloApplication.connection.executeQuery1(tempquery);
        if(temp.next()){
            productid=temp.getInt("max(productID)")+1;
        }
        String query=String.format("insert into product value(%s,'%s',%s,'%s')",productid,productName.getText(),price.getText(),sellerID.getText());
        int res=HelloApplication.connection.executeUpdate1(query);
        if(res>0){
            System.out.println("product is Added");
        }
        else{
            System.out.println("not added");
        }
    }
    @FXML
    public void logout(MouseEvent event) throws IOException {
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
}
