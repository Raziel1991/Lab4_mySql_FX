package com.example.lab4_sql_fx.display;

import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.controllers.PlayerController;
import com.example.lab4_sql_fx.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * This class defines a VBox layout that displays a table of Player data
 * and provides controls for interacting with the data, such as loading,
 * adding, and removing players.
 */
public class PlayerTableBox extends VBox {
    private final ObservableList<Player> playerList; // ObservableList to hold Player objects.
    private PlayerController playerController;
    /**
     * Constructor for PlayerTableBox. Initializes the table and its controls.
     *
     * @param sqlStuff An instance of SqlStuff to handle database operations.
     */
    public PlayerTableBox(SqlStuff sqlStuff) {
        playerController = new PlayerController(sqlStuff);
        playerList = FXCollections.observableArrayList(); // Initialize the player list.

        // TableView to display Player data.
        TableView<Player> tableView = new TableView<>();

        // Define columns for the table.
        // Column 1: Player ID
        TableColumn<Player, Integer> colPlayerId = new TableColumn<>("ID");
        colPlayerId.setCellValueFactory(new PropertyValueFactory<>("player_id"));

        // Column 2: First Name
        TableColumn<Player, String> colFirstName = createColumn("First Name", "first_name");

        // Column 3: Last Name
        TableColumn<Player, String> colLastName = createColumn("Last Name", "last_name");

        // Column 4: Address
        TableColumn<Player, String> colAddress = createColumn("Address", "address");

        // Column 5: Postal Code
        TableColumn<Player, String> colPostalCode = createColumn("Postal Code", "postal_code");

        // Column 6: Province
        TableColumn<Player, String> colProvince = createColumn("Province", "province");

        // Column 7: Phone Number
        TableColumn<Player, String> colPhoneNumber = createColumn("Phone Number", "phone_number");

        // Set the table to be editable and add all the columns.
        tableView.setEditable(true);
        tableView.getColumns().addAll(colPlayerId, colFirstName, colLastName, colAddress, colPostalCode, colProvince, colPhoneNumber);

        // Create a label for the table title.
        Label lblPlayer = new Label("Player");
        lblPlayer.setAlignment(Pos.CENTER);
        lblPlayer.setMaxWidth(Double.MAX_VALUE);

        // Button to load player data into the table.
        Button btnLoadTable = new Button("Load Table");
        btnLoadTable.setAlignment(Pos.CENTER);
        btnLoadTable.setMaxWidth(Double.MAX_VALUE);


        btnLoadTable.setOnAction(e -> {
            PlayerController playerController = new PlayerController(sqlStuff);
            if (sqlStuff != null) {
                // Fetch all player data and update the table.
                playerList.clear();
                playerList.addAll(playerController.getAllPlayers());
                tableView.getItems().setAll(playerList);

            }
        });

        // GridPane containing additional controls for managing players.
        GridPane gridPaneControllers = getGridControllers(tableView);
        GridPane.setMargin(tableView, new Insets(20, 0, 0, 0));

        // Add components to the VBox layout.
        this.setSpacing(10);
        this.getChildren().addAll(lblPlayer, tableView, btnLoadTable, gridPaneControllers);
    }

    /**
     * Creates a table column for a specific property of the Player class.
     *
     * @param columnName    The display name of the column.
     * @param columnProperty The property name in the Player class to bind to.
     * @return A configured TableColumn instance.
     */
    private static TableColumn<Player, String> createColumn(String columnName, String columnProperty) {
        TableColumn<Player, String> tableColumn = new TableColumn<>(columnName);
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(columnProperty));
        tableColumn.setCellFactory(TextFieldTableCell.forTableColumn()); // Allow editing.
        tableColumn.setEditable(true);
        return tableColumn;
    }

    /**
     * Creates a GridPane layout with input fields and buttons for managing players.
     *
     * @return A configured GridPane instance.
     */
    private GridPane getGridControllers(TableView<Player> tableView) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Labels for input fields.
        Label lblFirstName = new Label("First Name");
        Label lblLastName = new Label("Last Name");
        Label lblAddress = new Label("Address");
        Label lblPostalCode = new Label("Postal Code");
        Label lblProvince = new Label("Province");
        Label lblPhoneNumber = new Label("Phone Number");

        // Text fields for user input.
        TextField txtFirstName = new TextField();
        TextField txtLastName = new TextField();
        TextField txtAddress = new TextField();
        TextField txtPostalCode = new TextField();
        TextField txtProvince = new TextField();
        TextField txtPhoneNumber = new TextField();

        // Add labels and text fields to the grid.
        gridPane.add(lblFirstName, 0, 0);
        gridPane.add(txtFirstName, 0, 1);
        gridPane.add(lblLastName, 1, 0);
        gridPane.add(txtLastName, 1, 1);
        gridPane.add(lblAddress, 2, 0);
        gridPane.add(txtAddress, 2, 1);
        gridPane.add(lblPostalCode, 3, 0);
        gridPane.add(txtPostalCode, 3, 1);
        gridPane.add(lblProvince, 4, 0);
        gridPane.add(txtProvince, 4, 1);
        gridPane.add(lblPhoneNumber, 5, 0);
        gridPane.add(txtPhoneNumber, 5, 1);

        // Button to add a new player.
        Button newButton = new Button("New Player");
        newButton.setMaxWidth(Double.MAX_VALUE);
        newButton.setAlignment(Pos.CENTER);
        newButton.setOnAction(e -> {
            // Action for adding a new player
            Player currentPlayer = new Player(txtFirstName.getText(),
                    txtLastName.getText().trim(),
                    txtAddress.getText().trim(),
                    txtPostalCode.getText().trim(),
                    txtProvince.getText().trim(),
                    txtPhoneNumber.getText().trim()
            );

            if(playerController.insertPlayer(currentPlayer)){
                playerList.clear();
                playerList.addAll(playerController.getAllPlayers());
                tableView.getItems().setAll(playerList);
            }
        });

        // Button to remove an existing player.
        Button removePlayerButton = new Button("Remove Player");
        removePlayerButton.setMaxWidth(Double.MAX_VALUE);
        removePlayerButton.setAlignment(Pos.CENTER);
        removePlayerButton.setOnAction(e -> {
            // Action for removing a player
            Player selectedPlayer = tableView.getSelectionModel().getSelectedItem();
            // Remove the selected game and update the table view
            // Remove the selected game and update the table view
            if (selectedPlayer != null) {
                if (playerController.removePlayer(selectedPlayer.getPlayer_id())) {
                    playerList.remove(selectedPlayer);
                    playerList.clear();
                    playerList.addAll(playerController.getAllPlayers());
                    tableView.getItems().setAll(playerList);
                }
            }

        });

        Button btnCommitChanges = createCommitChangesButton(tableView);

        // Span buttons across multiple columns for better layout.
        GridPane.setColumnSpan(btnCommitChanges, 2);
        GridPane.setColumnSpan(newButton, 2);
        GridPane.setColumnSpan(removePlayerButton, 2);
        gridPane.add(newButton, 2, 2);
        gridPane.add(removePlayerButton, 2, 3);
        gridPane.add(btnCommitChanges, 2, 4);

        return gridPane;
    }
    private Button createCommitChangesButton(TableView<Player> tableView) {
        Button btnCommitChanges = new Button("Commit Changes");
        btnCommitChanges.setAlignment(Pos.CENTER);
        btnCommitChanges.setMaxWidth(Double.MAX_VALUE);

        btnCommitChanges.setOnAction(e -> {
            // Get the selected player from the table view
            Player selectedPlayer = tableView.getSelectionModel().getSelectedItem();

            // Only attempt to update if a player is selected and has a valid ID
            if (selectedPlayer != null && selectedPlayer.getPlayer_id() > 0) {
                boolean updateSuccess = playerController.updatePlayer(selectedPlayer);
                if (!updateSuccess) {
                    // Show an error dialog if update fails
                    showAlert("Update Failed", "Could not update player: " + selectedPlayer.getFirst_name() + " " + selectedPlayer.getLast_name());
                } else {
                    // Update the table view after a successful update
                    int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                    tableView.getItems().set(selectedIndex, selectedPlayer);
                }
            } else {
                showAlert("No Selection", "Please select a player to commit changes.");
            }
        });


        return btnCommitChanges;
    }

    /**
     * Shows an alert dialog
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}