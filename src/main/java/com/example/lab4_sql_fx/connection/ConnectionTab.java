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
        BorderPane pane = new BorderPane();

        //Top Layout
        Label label = new Label(s);
        StackPane topStackPane = new StackPane(label);
        pane.setPadding(new Insets(10, 10, 10, 10));
        topStackPane.setAlignment(Pos.CENTER);
        pane.setTop(topStackPane);

        //BorderPane Center Layout
        InfoDataGrid infoDataGrid = new InfoDataGrid();
        ConnectionButton connectionButton = new ConnectionButton(label, sqlStuff, infoDataGrid );
        Button genericButton = new Button("Generic Connection");
        VBox vbox = new VBox(infoDataGrid, genericButton, connectionButton);
        pane.setCenter(vbox);



        this.setContent(pane);
    }
}
