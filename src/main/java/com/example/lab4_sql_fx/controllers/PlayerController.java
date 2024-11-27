package com.example.lab4_sql_fx.controllers;

import com.example.lab4_sql_fx.SqlStuff;
import com.example.lab4_sql_fx.models.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerController {
    private final SqlStuff sqlStuff;

    public PlayerController(SqlStuff sqlStuff) {
        this.sqlStuff = sqlStuff;
    }

    //Insert a new game to the database
    public boolean insertPlayer(Player player) {
        String sql = "INSERT INTO player (first_name, last_name, address, postal_code, province, phone_number) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement pstmt = sqlStuff.getconnection().prepareStatement(sql)) {
            pstmt.setString(1, player.getFirst_name());
            pstmt.setString(2, player.getLast_name());
            pstmt.setString(3, player.getAddress());
            pstmt.setString(4, player.getPostal_code());
            pstmt.setString(5, player.getProvince());
            pstmt.setString(6, player.getPhone_number());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting player: " + e.getMessage());
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

    public ObservableList<Player> getAllPlayers() {
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

    public boolean updatePlayer(Player player) {
        try {
            String sql = "UPDATE Player SET first_name=?, last_name=?, address=?, " +
                    "postal_code=?, province=?, phone_number=? WHERE player_id=?";

            PreparedStatement pstmt = sqlStuff.getconnection().prepareStatement(sql);
            pstmt.setString(1, player.getFirst_name());
            pstmt.setString(2, player.getLast_name());
            pstmt.setString(3, player.getAddress());
            pstmt.setString(4, player.getPostal_code());
            pstmt.setString(5, player.getProvince());
            pstmt.setString(6, player.getPhone_number());
            pstmt.setInt(7, player.getPlayer_id());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
