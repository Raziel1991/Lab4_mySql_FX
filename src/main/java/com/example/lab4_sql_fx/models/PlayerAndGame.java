package com.example.lab4_sql_fx.models;

import java.util.Date;

public class PlayerAndGame {
    private int player_game_id;  // Primary key
    private int game_id;         // Foreign key to Game table
    private int player_id;       // Foreign key to Player table
    private Date playing_date;   // Date when the player played the game
    private int score;           // Score the player achieved

    // Constructor for inserting a new record (without player_game_id)
    public PlayerAndGame(int game_id, int player_id, Date playing_date, int score) {
        if (game_id <= 0) throw new IllegalArgumentException("game_id must be positive");
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        if (playing_date == null) throw new IllegalArgumentException("playing_date cannot be null");
        if (score < 0) throw new IllegalArgumentException("score cannot be negative");

        this.game_id = game_id;
        this.player_id = player_id;
        this.playing_date = playing_date;
        this.score = score;
    }

    // Constructor for updating a record (with player_game_id)
    public PlayerAndGame(int player_game_id, int game_id, int player_id, Date playing_date, int score) {
        if (player_game_id <= 0) throw new IllegalArgumentException("player_game_id must be positive");
        if (game_id <= 0) throw new IllegalArgumentException("game_id must be positive");
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        if (playing_date == null) throw new IllegalArgumentException("playing_date cannot be null");
        if (score < 0) throw new IllegalArgumentException("score cannot be negative");

        this.player_game_id = player_game_id;
        this.game_id = game_id;
        this.player_id = player_id;
        this.playing_date = playing_date;
        this.score = score;
    }

    // Getters and Setters

    public int getPlayer_game_id() {
        return player_game_id;
    }

    public void setPlayer_game_id(int player_game_id) {
        if (player_game_id <= 0) throw new IllegalArgumentException("player_game_id must be positive");
        this.player_game_id = player_game_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        if (game_id <= 0) throw new IllegalArgumentException("game_id must be positive");
        this.game_id = game_id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        if (player_id <= 0) throw new IllegalArgumentException("player_id must be positive");
        this.player_id = player_id;
    }

    public Date getPlaying_date() {
        return playing_date;
    }

    public void setPlaying_date(Date playing_date) {
        if (playing_date == null) throw new IllegalArgumentException("playing_date cannot be null");
        this.playing_date = playing_date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) throw new IllegalArgumentException("score cannot be negative");
        this.score = score;
    }

    @Override
    public String toString() {
        return "PlayerAndGame{" +
                "player_game_id=" + player_game_id +
                ", game_id=" + game_id +
                ", player_id=" + player_id +
                ", playing_date=" + playing_date +
                ", score=" + score +
                '}';
    }
}
