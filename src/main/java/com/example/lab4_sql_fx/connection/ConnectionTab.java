package com.example.lab4_sql_fx.connection;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ConnectionTab extends Tab {

    public ConnectionTab(String s, BorderPane borderPane, Label label, Button button) {
        super(s);
        this.setContent(borderPane);

        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20));

        // Pane Layout
        borderPane.setTop(hBox);

        borderPane.setCenter(button);
    }

//    public void setLabelColor(Color labelColor) {
//        this.labelColor = labelColor;
//        label.setBackground(new Background(new BackgroundFill(this.labelColor, CornerRadii.EMPTY, Insets.EMPTY)));
//    }
}
