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

/**
 * ConnectionButton class for creating a styled button to manage database connections.
 */
public class ConnectionButton extends Button {

    /**
     * Creates and configures a visually enhanced button for connecting and disconnecting from a database.
     *
     * @param label    A Label that displays the connection status with updated styling.
     * @param sqlStuff An instance of SqlStuff that handles database operations.
     * @return A styled Button for managing the connection.
     */
    public static Button setConnectionButton(Label label, SqlStuff sqlStuff, InfoDataGrid infoDataGrid) {

        // Create a button with an initial label
        Button myButton = new Button("Connect to Database");
        myButton.setFont(Font.font("Arial", 16));
        myButton.setPadding(new Insets(10));
        myButton.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Add a tooltip for the button
        Tooltip tooltip = new Tooltip("Click to toggle database connection");
        tooltip.setFont(Font.font("Arial", 12));
        myButton.setTooltip(tooltip);

        // Style the label to make it more visually appealing
        label.setFont(Font.font("Arial", 14));
        label.setPadding(new Insets(10));
        label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), Insets.EMPTY)));
        label.setTextFill(Color.WHITE);
        label.setStyle("-fx-border-radius: 5; -fx-background-radius: 5; -fx-alignment: center;");

        // Define the button's action when clicked
        myButton.setOnAction(e -> {
            if (!sqlStuff.isConnectionStatus()) { // If not currently connected
                if (sqlStuff != null) { // Ensure sqlStuff instance is not null
                    try {
                        // Attempt to start the database connection
                        sqlStuff.startConnection(infoDataGrid.getURL(), infoDataGrid.getUsername(), infoDataGrid.getPassword());

                        // Update label and button for the connected state
                        label.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(5), Insets.EMPTY)));
                        label.setText("Connected to Database");
                        myButton.setText("Disconnect from Database");
                        myButton.setStyle("-fx-background-color: #E81123; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");
                        System.out.println(sqlStuff.isConnectionStatus());

                    } catch (SQLException ex) {
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
                }
            } if(sqlStuff.isConnectionStatus()) {
                // If already connected, close the database connection
                sqlStuff.closeConnection();

                // Update label and button for the disconnected state
                label.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(5), Insets.EMPTY)));
                label.setText("Disconnected from Database");
                myButton.setText("Connect to Database");
                myButton.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-border-radius: 5; -fx-background-radius: 5;");
                System.out.println(sqlStuff.isConnectionStatus());
            }
        });

        // Return the styled button
        return myButton;
    }
}
