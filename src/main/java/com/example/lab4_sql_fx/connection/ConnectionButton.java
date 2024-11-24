package com.example.lab4_sql_fx.connection;

import com.example.lab4_sql_fx.SqlStuff;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ConnectionButton extends Button {

    public ConnectionButton(Label label, SqlStuff sqlStuff, InfoDataGrid infoDataGrid) {
        // Set initial text
        this.setText("Connect");
        //this.setMaxWidth();

        // Add a tooltip
        Tooltip tooltip = new Tooltip("Click to toggle database connection");
        this.setTooltip(tooltip);


        // Style the button
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.color(0, 0, 0, 0.5));
        this.setEffect(dropShadow);
        this.setAlignment(Pos.CENTER);
        this.setFont(Font.font("Arial", 16)); // Change font and size
        this.setTextFill(Color.WHITE); // Text color
        this.setStyle("-fx-background-color: linear-gradient(to right, #2ba1f8 70%, #1e81ee);" + // Green gradient
                "-fx-background-radius: 25px;" + // Rounded corners
                "-fx-text-fill: #c7e6ff;" + // Border color
                "-fx-border-width: 2px;" + // Border thickness
                "-fx-border-radius: 30px;" + // Border rounded
                "-fx-padding: 10px 20px;"  + // Padding inside button
                "-fx-transition: all 0.3s ease;"
                );

        // Add hover effect
        this.setOnMouseEntered(event ->

                this.setStyle("-fx-background-color: linear-gradient(to right, #66BB6A, #43A047);" +
                //"-fx-border-color: #087F23;" +
                "-fx-background-radius: 25px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 30px;" +
                "-fx-padding: 10px 20px;"));

        this.setOnMouseExited(event -> this.setStyle("-fx-background-color: linear-gradient(to right, #2ba1f8 70%, #1e81ee);" +
                //"-fx-border-color: #065C16;" +
                "-fx-background-radius: 25px;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 30px;" +
                "-fx-padding: 10px 20px;"));

        // Define the button's action when clicked
        this.setOnAction(e -> {
            if (!sqlStuff.isConnectionStatus()) {
                try {
                    sqlStuff.startConnection(infoDataGrid.getURL(), infoDataGrid.getUsername(), infoDataGrid.getPassword());
                    label.setText("Connected to Database");
                    this.setText("Disconnect from Database");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Label errorLabel = new Label(ex.getMessage());
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(errorLabel);
                    Scene scene = new Scene(stackPane, 600, 200);
                    Stage errorNewWindow = new Stage();
                    errorNewWindow.setTitle("Connection Error");
                    errorNewWindow.setScene(scene);
                    errorNewWindow.show();
                }
            } else {
                sqlStuff.closeConnection();
                label.setText("Disconnected from Database");
                this.setText("Connect to Database");
            }
        });
    }
}
