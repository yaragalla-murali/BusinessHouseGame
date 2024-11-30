package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

@Entity
public class Player {

    @Column(name = "player_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int totalBalance = 1000;
    private int currentPositionOnBoard;
    private int playerPosition;

    public Player() {
    }

    public Player(int id, String testPlayer) {
        this.id = id;
        this.name = testPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public int getCurrentPositionOnBoard() {
        return currentPositionOnBoard;
    }

    public void setCurrentPositionOnBoard(int currentPositionOnBoard) {
        this.currentPositionOnBoard = currentPositionOnBoard;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
