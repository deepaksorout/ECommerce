package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        ResultSet res=null;
        String query=String.format("select * from user where email='%s' and pass='%s'",email.getText(),password.getText());
        res = HelloApplication.connection.executeQuery1(query);
        if(res.next()){
            HelloApplication.emailid= res.getString("email");
            String usertype=res.getString("usertype");
            if(usertype.equals("seller")){
                AnchorPane seller= FXMLLoader.load(getClass().getResource("sellerPage.fxml"));
                HelloApplication.root.getChildren().add(seller);
            }
            else{
                //System.out.println("buyer page");
                productPage productpage=new productPage();
                Header header=new Header();

                AnchorPane productpane=new AnchorPane();
                productpane.getChildren().addAll(productpage.products());

                productpane.setLayoutX(150);
                productpane.setLayoutY(100);

                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productpane);
            }
            //System.out.println("user is present");
        }
        else{
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed, Please check EmailID/Password");
            dialog.showAndWait();
        }
    }
}
