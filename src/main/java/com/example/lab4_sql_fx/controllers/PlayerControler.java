package com.example.lab4_sql_fx.controllers;

import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerControler {
    private final SqlStuff sqlStuff;

    public PlayerControler(SqlStuff sqlStuff) {
        this.sqlStuff = sqlStuff;
    }

    //Insert a new game to the database
    public boolean insertPlayer(Player player) {
        String sql = "insert into player (player_id, first_name, last_name, address, postal_code, province, phone_number) values(?,?,?,?,?,?,?)";
        try (PreparedStatement pstmt = sqlStuff.getconnection().prepareStatement(sql)){
            pstmt.setInt(1,player.getPlayer_id());
            pstmt.setString(2,player.getFirst_name());
            pstmt.setString(3,player.getLast_name());
            pstmt.setString(4,player.getAddress());
            pstmt.setString(5,player.getPostal_code());
            pstmt.setString(6,player.getProvince());
            pstmt.setString(7,player.getPhone_number());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting game: " + e.getMessage());
            return false;
        }
    }

    public boolean removePlayer(int playerId) {
        String sql = "delete from player where player_id = ?";
        try(PreparedStatement pstmt  = sqlStuff.getconnection().prepareStatement(sql)){
            pstmt.setInt(1,playerId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ObservableList<Player> getAllGames() {
        ObservableList<Player> players = FXCollections.observableArrayList();
        String sql = "select * from player";
        try (Statement stmt = sqlStuff.getconnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("province"),
                        rs.getString("phone_number")
                );
                players.add(player);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }
}
