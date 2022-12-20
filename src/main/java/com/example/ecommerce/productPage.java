package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.xml.sax.HandlerBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class productPage {
    ListView<HBox> products;

    ListView<HBox> productsbysearch(String search) throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productlist = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery1("select * from product");
        while (res.next()) {
            if (res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productID = new Label();
                Label price = new Label();
                Button buy = new Button();
                buy.setText("Buy");
                HBox productdetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailid.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("You are not Logged in, Please login");
                            dialog.showAndWait();
                        } else {
                            System.out.println("You are logged in with " + HelloApplication.emailid);
                            order order = new order();
                            try {
                                order.placeorder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        //System.out.println("clicked");
                    }
                });

                name.setMinWidth(50);
                price.setMinWidth(50);
                productID.setMinWidth(50);

                name.setText(res.getString("productName"));
                productID.setText(res.getString("productID"));
                price.setText(res.getString("price"));
                productdetails.getChildren().addAll(productID, name, price, buy);
                productlist.add(productdetails);
            }
        }
            products.setItems(productlist);
            return products;
    }

    ListView<HBox> products() throws SQLException {
        products=new ListView<>();
        ObservableList<HBox> productlist= FXCollections.observableArrayList();
        ResultSet res=HelloApplication.connection.executeQuery1("select * from product");
        while(res.next()) {
            Label name = new Label();
            Label productID = new Label();
            Label price = new Label();
            Button buy = new Button();
            buy.setText("Buy");
            HBox productdetails = new HBox();

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (HelloApplication.emailid.equals("")) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("You are not Logged in, Please login");
                        dialog.showAndWait();
                    } else {
                        System.out.println("You are logged in with " + HelloApplication.emailid);
                        order order = new order();
                        try {
                            order.placeorder(productID.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    //System.out.println("clicked");
                }
            });

            name.setMinWidth(50);
            price.setMinWidth(50);
            productID.setMinWidth(50);

            name.setText(res.getString("productName"));
            productID.setText(res.getString("productID"));
            price.setText(res.getString("price"));
            productdetails.getChildren().addAll(productID, name, price, buy);
            productlist.add(productdetails);
        }
        products.setItems(productlist);
        return products;
    }
}
