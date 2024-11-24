module com.example.lab4_sql_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.lab4_sql_fx to javafx.fxml;
    opens com.example.lab4_sql_fx.models to javafx.base;
    opens com.example.lab4_sql_fx.display to javafx.graphics;

    exports com.example.lab4_sql_fx;
    exports com.example.lab4_sql_fx.connection;
    opens com.example.lab4_sql_fx.connection to javafx.fxml;
}