package com.example.lab4_sql_fx.connection;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class InfoDataGrid extends GridPane {

    private TextField txtURL = new TextField();
    private TextField txtUserName = new TextField();
    private PasswordField pwField = new PasswordField();


    public InfoDataGrid() {
        Label lblURL = new Label("URL", txtURL);
        Label lblUserName = new Label("Username", txtUserName);
        Label lblPassword = new Label("Password", pwField);

        txtURL.setText("jdbc:mysql://127.0.0.1:3306/users_db");

        add(lblURL, 0, 1);
        add(lblUserName, 0, 2);
        add(lblPassword, 0, 3);

    }

    public String getURL() {
        return txtURL.getText();
    }
    public String getUsername() {
        return txtUserName.getText();
    }
    public String getPassword() {
        return pwField.getText();
    }
}
//this.txtURL.setText("jdbc:mysql://127.0.0.1:3306/users_db");