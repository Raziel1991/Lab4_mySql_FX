package com.example.lab4_sql_fx.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Game {
    private final SimpleIntegerProperty gameId;
    private final SimpleStringProperty gameTitle;

    public Game(int gameId, String gameTitle) {
        if (gameId <= 0) throw new IllegalArgumentException("gameId must be positive");
        if (gameTitle == null || gameTitle.trim().isEmpty()) throw new IllegalArgumentException("gameTitle cannot be null or empty");
        this.gameId = new SimpleIntegerProperty(gameId);
        this.gameTitle = new SimpleStringProperty(gameTitle);
    }

    // Standard getter for gameId
    public int getGameId() {
        return gameId.get();
    }

    // Property getter for gameId
    public SimpleIntegerProperty gameIdProperty() {
        return gameId;
    }

    // Setter for gameId
    public void setGameId(int gameId) {
        if (gameId <= 0) throw new IllegalArgumentException("gameId must be positive");
        this.gameId.set(gameId);
    }

    // Standard getter for gameTitle
    public String getGameTitle() {
        return gameTitle.get();
    }

    // Property getter for gameTitle
    public SimpleStringProperty gameTitleProperty() {
        return gameTitle;
    }

    // Setter for gameTitle
    public void setGameTitle(String gameTitle) {
        if (gameTitle == null || gameTitle.trim().isEmpty()) throw new IllegalArgumentException("gameTitle cannot be null or empty");
        this.gameTitle.set(gameTitle);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId.get() +
                ", gameTitle='" + gameTitle.get() + '\'' +
                '}';
    }
}