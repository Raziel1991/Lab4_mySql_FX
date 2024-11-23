package com.example.lab4_sql_fx.connection;
import com.example.lab4_sql_fx.SqlStuff;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.SQLException;


public class ConnectionButton extends Button {

    public ConnectionButton(Label label, SqlStuff sqlStuff, InfoDataGrid infoDataGrid) {
        // Create a button with an initial label
        this.setText("Connect");
        this.setFont(Font.font("Arial", 16));
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");
        // Add a tooltip for the button
        Tooltip tooltip = new Tooltip("Click to toggle database connection");
        tooltip.setFont(Font.font("Arial", 12));
        this.setTooltip(tooltip);

        // Style the label to make it more visually appealing
        label.setFont(Font.font("Arial", 14));
        label.setPadding(new Insets(10));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-background-color: #606d72; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Define the button's action when clicked

        this.setOnAction(e ->{
            if (!sqlStuff.isConnectionStatus()){
                try {
                    sqlStuff.startConnection(infoDataGrid.getURL(), infoDataGrid.getUsername(), infoDataGrid.getPassword());
                    label.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), Insets.EMPTY)));
                    label.setText("Connected to Database");
                    this.setText("Disconnect from Database");
                    this.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");
                    System.out.println(sqlStuff.isConnectionStatus());

                }catch (SQLException ex){
                    ex.printStackTrace();
                    Label erroLabel = new Label(ex.getMessage());
                    erroLabel.setFont(Font.font("Arial", 14));
                    erroLabel.setPadding(new Insets(10));
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(erroLabel);
                    Scene scene =  new Scene(stackPane, 600, 200);
                    Stage errorNewWindow = new Stage();
                    errorNewWindow.setTitle("Connection Error");
                    errorNewWindow.setScene(scene);
                    errorNewWindow.show();
                }
            }else{

                this.setText("Connect to Database");
                sqlStuff.closeConnection();
                label.setText("Disconnected from Database1");
                label.setStyle("-fx-background-color: #f44848; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");
            }
        });
    }
}