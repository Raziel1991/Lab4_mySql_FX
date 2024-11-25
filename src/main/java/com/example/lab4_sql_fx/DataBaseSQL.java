package com.example.lab4_sql_fx;

import com.example.lab4_sql_fx.connection.ConnectionButton;
import com.example.lab4_sql_fx.connection.ConnectionTab;
import com.example.lab4_sql_fx.connection.InfoDataGrid;
import com.example.lab4_sql_fx.display.DisplayDataTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;


public class DataBaseSQL extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Start DB connection
        Connection connection;
        SqlStuff sqlStuff = new SqlStuff();
        connection = sqlStuff.getconnection();



        TabPane tabPane = new TabPane();
        ConnectionTab connectionTab = new ConnectionTab("Connection", sqlStuff);
        DisplayDataTab displayDataTab = new DisplayDataTab("Display", sqlStuff);


        tabPane.getTabs().addAll(connectionTab,displayDataTab );
        Scene scene = new Scene(tabPane, 800, 800);



        stage.setTitle("DataBaseSQL!");
        stage.setScene(scene);
        stage.show();


        //TODO:end connections when closed
        sqlStuff.closeConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}