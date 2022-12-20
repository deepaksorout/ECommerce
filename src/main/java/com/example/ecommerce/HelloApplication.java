package com.example.ecommerce;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static Group root;
    public static String emailid;
    public static DatabaseConnection connection;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        emailid="";
        connection=new DatabaseConnection();
        root=new Group();
        Header head=new Header();
        productPage productpage=new productPage();
        AnchorPane productpane=new AnchorPane();
        productpane.getChildren().add(productpage.products());

        productpane.setLayoutX(150);
        productpane.setLayoutY(100);
        root.getChildren().addAll(head.root,productpane);

       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("E-Commerce");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e->{
            try {
                connection.con.close();
                //System.out.println("Connection close");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}