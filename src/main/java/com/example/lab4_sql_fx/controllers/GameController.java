package com.example.lab4_sql_fx.controllers;
import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.models.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class GameController {
    private final SqlStuff sqlStuff;

    public GameController(SqlStuff sqlStuff) {
        this.sqlStuff = sqlStuff;
    }

    //Insert a new game to the database
    public boolean insertGame(String gameTitle) {
        String sql = "insert into game (game_id, game_title) values(?,?)";
        try (PreparedStatement pstmt = sqlStuff.getconnection().prepareStatement(sql)){
            pstmt.setString(1, null);
            pstmt.setString(2,gameTitle);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting game: " + e.getMessage());
            return false;
        }
    }

    public boolean removeGame(int gameID) {
        String sql = "delete from game where game_id = ?";
        try(PreparedStatement pstmt  = sqlStuff.getconnection().prepareStatement(sql)){
            pstmt.setInt(1,gameID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ObservableList<Game> getAllGames() {
        ObservableList<Game> games = FXCollections.observableArrayList();
        String sql = "select * from game";
        try (Statement stmt = sqlStuff.getconnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                Game game = new Game(
                        rs.getInt("game_id"),
                        rs.getString("game_title")
                );
                games.add(game);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }
}
