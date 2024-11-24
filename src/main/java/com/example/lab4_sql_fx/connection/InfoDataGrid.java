package com.example.lab4_sql_fx.connection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InfoDataGrid extends GridPane {

    private TextField txtURL = new TextField();
    private TextField txtUserName = new TextField();
    private PasswordField pwField = new PasswordField();
    private final int fontsize = 16;
    public InfoDataGrid() {
        // Create labels
        Label lblURL = new Label("URL");
        Label lblUserName = new Label("Username");
        Label lblPassword = new Label("Password");

        lblURL.setTextFill(Color.WHITE);
        lblUserName.setTextFill(Color.WHITE);
        lblPassword.setTextFill(Color.WHITE);
        lblURL.setFont(new Font("Calibri", fontsize));
        lblUserName.setFont(new Font("Calibri", fontsize));
        lblPassword.setFont(new Font("Calibri", fontsize));

        // Set tooltips
        lblURL.setTooltip(new Tooltip("jdbc:mysql://server:port/database"));
        lblUserName.setTooltip(new Tooltip("Commonly the root account"));
        lblPassword.setTooltip(new Tooltip("Commonly the root password"));

        // Set default values
        txtURL.setText("jdbc:mysql://127.0.0.1:3306/users_db");
        txtUserName.setText("root");
        pwField.setText("Password");
        txtUserName.setPromptText("root");
        pwField.setPromptText("password");

        // Add components to the grid
        add(lblURL, 0, 0);
        add(txtURL, 1, 0);
        add(lblUserName, 0, 1);
        add(txtUserName, 1, 1);
        add(lblPassword, 0, 2);
        add(pwField, 1, 2);

        // Style the grid
        this.setAlignment(Pos.CENTER);
        setHgap(10); // Horizontal gap between columns
        setVgap(15); // Vertical gap between rows
        setPadding(new Insets(20)); // Padding around the grid
    }

    // Getters
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
