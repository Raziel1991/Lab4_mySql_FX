package com.example.lab4_sql_fx;
import com.example.lab4_sql_fx.connection.InfoDataGrid;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SqlStuff class for managing database connections.
 */
public class SqlStuff {

    // Static field for the database connection
    private static Connection con;

    // Status of the connection (true if connected, false otherwise)
    private boolean connectionStatus = false;

    public void startConnection(String URL, String USERNAME, String PASS) throws SQLException {
        con = null; // Initialize connection to null

        // Attempt to establish a connection
        con = DriverManager.getConnection(URL, USERNAME, PASS);
        connectionStatus = true; // Set status to true on success
        if (con != null) {
            System.out.println("Connection Established!");
            connectionStatus = true;
        }
    }

    /**
     * Closes the database connection if it is open.
     */
    public void closeConnection() {
        if (con != null) { // Check if the connection is not null
            try {
                con.close(); // Close the connection
                System.out.println("Connection Closed");
                connectionStatus = false; // Update status to false
            } catch (SQLException e) {
                throw new RuntimeException(e); // Handle SQL exceptions
            }
        }
    }

    /**
     * Gets the current database connection.
     *
     * @return the database connection object
     */
    public Connection getconnection() {
        return con;
    }

    /**
     * Checks the connection status.
     *
     * @return true if the connection is active, false otherwise
     */
    public boolean isConnectionStatus() {
        return connectionStatus;
    }
}
