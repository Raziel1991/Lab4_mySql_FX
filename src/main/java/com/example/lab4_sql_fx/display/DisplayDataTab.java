package com.example.lab4_sql_fx.display;
import com.example.lab4_sql_fx.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DisplayDataTab extends Tab {
    private final BorderPane borderPane = new BorderPane();
    private final ObservableList<Game> gameList;

    public DisplayDataTab(String title) {
        super(title);
        gameList = FXCollections.observableArrayList();
        loadSampleData();

        // Main title
        Label lblMainTitle = new Label("Games Data");
        lblMainTitle.setAlignment(Pos.CENTER);
        lblMainTitle.setMaxWidth(Double.MAX_VALUE);
        lblMainTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Layout setup
        borderPane.setTop(lblMainTitle);
        borderPane.setLeft(createGameTableView());
        borderPane.setPadding(new Insets(15)); // Add padding around the BorderPane

        this.setContent(borderPane);
    }

    private VBox createGameTableView() {
        // Create and populate the TableView
        TableView<Game> tableView = new TableView<>();

        // Create Game ID column
        TableColumn<Game, Integer> colGameId = new TableColumn<>("Game ID");
        colGameId.setCellValueFactory(new PropertyValueFactory<>("gameId")); // Changed from game_id

        colGameId.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3)); // 30% width

        // Create Game Title column
        TableColumn<Game, String> colGameTitle = new TableColumn<>("Game Title");
        colGameTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle")); // Changed from game_title
        colGameTitle.prefWidthProperty().bind(tableView.widthProperty().multiply(0.7)); // 70% width

        // Add columns to TableView
        tableView.getColumns().addAll(colGameId, colGameTitle);

        // Bind data
        tableView.setItems(this.gameList);

        // Create layout container
        VBox vbox = new VBox(tableView);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        return vbox;
    }

    private void loadSampleData() {
        gameList.add(new Game(1, "Chess"));
        gameList.add(new Game(2, "Monopoly"));
        gameList.add(new Game(3, "Scrabble"));
    }
}
