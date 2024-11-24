package com.example.lab4_sql_fx.connection;
import com.example.lab4_sql_fx.SqlStuff;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;


public class ConnectionTab extends Tab {
    public ConnectionTab(String s, SqlStuff sqlStuff) {
        super(s);

        //Sets tab Style
        this.setStyle("-fx-background-color: #42cccf;");
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-background-color: linear-gradient(to right, #252422 75%, #252430 );");

        //Top Layout
        Label label = new Label(s);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);

        label.setStyle("-fx-background-color: #333332;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20px;"
        );


        StackPane topStackPane = new StackPane(label);
        pane.setPadding(new Insets(10, 10, 10, 10));
        topStackPane.setAlignment(Pos.CENTER);
        pane.setTop(topStackPane);

        //BorderPane Center Layout
        InfoDataGrid infoDataGrid = new InfoDataGrid();
        ConnectionButton connectionButton = new ConnectionButton(label, sqlStuff, infoDataGrid );


        VBox vbox = new VBox(10, infoDataGrid, connectionButton);
        vbox.setAlignment(Pos.CENTER);

        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setMaxHeight(Double.MAX_VALUE);


        pane.setCenter(vbox);

        this.setContent(pane);
    }
}
