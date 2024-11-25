package com.example.lab4_sql_fx.display;

import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.controllers.GameController;
import com.example.lab4_sql_fx.controllers.PlayerControler;
import com.example.lab4_sql_fx.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class PlayerTableBox extends VBox{
    private final ObservableList<Player> playerList; // List of players (unused in this tab)
    public PlayerTableBox(SqlStuff sqlStuff) {
        playerList = FXCollections.observableArrayList();
        //7 total Columns
        TableView<Player> tableView = new TableView<>();

        //1
        TableColumn<Player, Integer> colPlayerId = new TableColumn<>("ID");
        colPlayerId.setCellValueFactory(new PropertyValueFactory<>("player_id"));
        //2
        TableColumn<Player, String> colFirstName = new TableColumn<>("First Name");
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colFirstName.setEditable(true);
        //3
        TableColumn<Player, String> colLastName = new TableColumn<>("Last Name");
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        //4
        TableColumn<Player, String> colAddress = new TableColumn<>("Age");
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        //5
        TableColumn<Player, String> colPostalCode = new TableColumn<>("Postal Code");
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postal_code"));
        //6
        TableColumn<Player, String> colProvince = new TableColumn<>("Province");
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        //7
        TableColumn<Player, String> colPhoneNumber = new TableColumn<>("Phone Number");
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colFirstName.setEditable(true);
        tableView.setEditable(true);
        tableView.getColumns().addAll(colPlayerId, colFirstName, colLastName, colAddress, colPostalCode, colProvince, colPhoneNumber);

        //Tittle Label
        Label lblPlayer = new Label("Player");
        lblPlayer.setAlignment(Pos.CENTER);
        lblPlayer.setMaxWidth(Double.MAX_VALUE);


        //Buttons, Remove, Add, Update, Load Table

        Button btnLoadTable = new Button("Load Table");
        btnLoadTable.setOnAction(e -> {
            PlayerControler playerControler = new PlayerControler(sqlStuff);
            if (sqlStuff !=null){
                playerList.addAll(playerControler.getAllGames());
                tableView.getItems().clear();
                tableView.getItems().addAll(playerList);
            }
        });


        this.getChildren().addAll(lblPlayer, tableView, btnLoadTable);
    }
}
