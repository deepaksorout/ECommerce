package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    Button loginbutton;
    @FXML
    Label id;
    @FXML
    public void initialize(){
        if(!HelloApplication.emailid.equals("")){
            loginbutton.setOpacity(0);
            id.setText(HelloApplication.emailid);
        }
        else{
            logoutbutton.setOpacity(0);
        }
    }
    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane loginpage= FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        HelloApplication.root.getChildren().add(loginpage);
    }
    @FXML
    TextField searchtext;
    @FXML
    public void search(MouseEvent event) throws SQLException, IOException {
        productPage productpage=new productPage();
        Header header=new Header();

        AnchorPane productpane=new AnchorPane();
        productpane.getChildren().addAll(productpage.productsbysearch(searchtext.getText()));

        productpane.setLayoutX(150);
        productpane.setLayoutY(100);

        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productpane);
    }
    @FXML
    Button logoutbutton;
    @FXML
    public void logout(MouseEvent event) throws IOException {
        HelloApplication.emailid="";
        logoutbutton.setOpacity(0);
        Header header=new Header();
        HelloApplication.root.getChildren().add(header.root);
    }
}
