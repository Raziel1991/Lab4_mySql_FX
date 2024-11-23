package com.example.lab4_sql_fx.connection;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;


public class ConnectionTab extends Tab {
    public ConnectionTab(String s) {
        super(s);
        BorderPane pane = new BorderPane();

        //Top Layout
        Label label = new Label(s);
        StackPane topStackPane = new StackPane(label);
        pane.setPadding(new Insets(10, 10, 10, 10));
        topStackPane.setAlignment(Pos.CENTER);
        pane.setTop(topStackPane);

        //BorderPane Center Layout
        InfoDataGrid infoDataGrid = new InfoDataGrid();
        pane.setCenter(infoDataGrid);


        this.setContent(pane);
    }
}
