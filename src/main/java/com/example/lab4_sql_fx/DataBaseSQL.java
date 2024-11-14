package com.example.lab4_sql_fx;
import static com.example.lab4_sql_fx.SqlStuff.closeConnection;
import static com.example.lab4_sql_fx.SqlStuff.startConnection;

import com.example.lab4_sql_fx.connection.ConnectionTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;


public class DataBaseSQL extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        TabPane tabPane = new TabPane();
        Scene scene = new Scene(tabPane, 800, 800);

        //Connection Tab Layout
        BorderPane borderConnections = new BorderPane();
        Button buttonConnect = new Button("Connect");
        Tab tabConnections = new ConnectionTab("Connections",borderConnections,new Label("Connections"),buttonConnect);
        tabPane.getTabs().add(tabConnections);



//        //Start DB connection
//        Connection connection;
//        connection = startConnection("jdbc:mysql://127.0.0.1:3306/users_db", "root","ArmaYsm1");
//
//        if (connection != null) {
//            System.out.println(connection);
//            closeConnection();
//        }else {
//            System.out.println("connection is null");
//        }
//

        stage.setTitle("DataBaseSQL!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}