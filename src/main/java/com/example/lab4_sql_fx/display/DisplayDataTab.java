package com.example.lab4_sql_fx.display;

import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.controllers.GameController;
import com.example.lab4_sql_fx.models.Game;
import com.example.lab4_sql_fx.models.Player;
import com.example.lab4_sql_fx.models.PlayerAndGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class defines a JavaFX Tab for displaying and managing game data.
 * It includes functionalities for loading, inserting, and deleting games.
 */
public class DisplayDataTab extends Tab {
    private final BorderPane borderPane = new BorderPane(); // Main layout container
    private final ObservableList<Game> gameList; // List of games to be displayed
    private final ObservableList<PlayerAndGame> playerAndGameList; // List of player-game relationships (unused in this tab)
    private final GameController gameController; // Controller for managing game data operations

    /**
     * Constructor for DisplayDataTab.
     *
     * @param title    The title of the tab.
     * @param sqlStuff The database helper class for managing SQL connections.
     */
    public DisplayDataTab(String title, SqlStuff sqlStuff) {
        super(title);
        gameList = FXCollections.observableArrayList();

        playerAndGameList = FXCollections.observableArrayList();
        gameController = new GameController(sqlStuff);

        // Main title label for the tab
        Label lblMainTitle = new Label("Games Data");
        lblMainTitle.setAlignment(Pos.CENTER);
        lblMainTitle.setMaxWidth(Double.MAX_VALUE);
        lblMainTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Set up the layout
        borderPane.setTop(lblMainTitle);
        borderPane.setLeft(createGameLeftView());
        borderPane.setPadding(new Insets(15)); // Add padding around the BorderPane


        PlayerTableBox playerTableBox = new PlayerTableBox(sqlStuff);


        borderPane.setCenter(playerTableBox);


        this.setContent(borderPane);
    }

    /**
     * Creates the left-side view for managing games.
     *
     * @return A VBox containing the layout for managing games.
     */
    private VBox createGameLeftView() {
        // Title label for the games section
        Label lblTitle = new Label("Game");
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setMaxWidth(Double.MAX_VALUE);
        lblTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // TableView for displaying games
        TableView<Game> tableView = new TableView<>();
        tableView.setMaxWidth(150);

        // Table column for game ID
        TableColumn<Game, Integer> colGameId = new TableColumn<>("ID");
        colGameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        colGameId.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3)); // Set column width to 30%

        // Table column for game title
        TableColumn<Game, String> colGameTitle = new TableColumn<>("Title");
        colGameTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        colGameTitle.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7)); // Set column width to 70%

        // Add columns to TableView
        tableView.getColumns().addAll(colGameId, colGameTitle);

        // Bind game data to the TableView
        tableView.setItems(this.gameList);

        // Controls for inserting a new game
        Label lblGameTitle = new Label("Game Title");
        TextField txtGameTitle = new TextField();
        lblGameTitle.setPadding(new Insets(0, 10, 0, 0));
        lblGameTitle.setAlignment(Pos.CENTER);
        txtGameTitle.setMaxWidth(80);

        // Horizontal box for label and text field
        HBox hBox2 = new HBox(lblGameTitle, txtGameTitle);
        hBox2.setMaxWidth(150);

        // Button for inserting a new game
        Button btnGameInsert = new Button("Insert Game");
        btnGameInsert.setMaxWidth(150);
        btnGameInsert.setOnAction(e -> {
            if (!txtGameTitle.getText().isEmpty()) {
                if (gameController.insertGame(txtGameTitle.getText())) {
                    // Refresh game list after insertion
                    gameList.clear();
                    gameList.addAll(gameController.getAllGames());
                }
            }
        });

        // Button for loading all games
        Button btnLoad = new Button("Load Games");
        btnLoad.setMaxWidth(150);
        btnLoad.setOnAction(e -> {
            // Refresh game list with data from the database
            gameList.clear();
            gameList.addAll(gameController.getAllGames());
        });

        // Button for removing a selected game
        Button btnRemove = new Button("Remove Game");
        btnRemove.setMaxWidth(150);
        btnRemove.setOnAction(e -> {
            Game selectedGame = tableView.getSelectionModel().getSelectedItem();

            // Remove the selected game and update the table view
            if (selectedGame != null) {
                if (gameController.removeGame(selectedGame.getGameId())) {
                    gameList.remove(selectedGame);
                }
            }
        });

        // Create a VBox layout for the left view
        VBox vbox = new VBox(lblTitle, tableView, hBox2, btnGameInsert, btnLoad, btnRemove);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        return vbox;
    }


}
