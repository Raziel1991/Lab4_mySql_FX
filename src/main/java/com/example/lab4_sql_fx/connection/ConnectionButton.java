package com.example.lab4_sql_fx.connection;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ConnectionButton extends Button {


    public Button setConnectionButton(Label label){
        Button myButton = new Button("Change Color");
        Background background = new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY));
        myButton.setOnAction(e -> {
            label.setBackground(background);
        });


        return myButton;
    }

}
