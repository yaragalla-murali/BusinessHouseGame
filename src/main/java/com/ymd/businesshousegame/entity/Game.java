package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {

    @Column(name = "Game_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @OrderBy("playerPosition")
    private List<Player> players = new ArrayList<>();
    @OneToOne
    private Player nextPlayer;

    @OneToOne
    private Board board;

    @OneToOne
    private Dice dice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private GameStatus status;

    private int numberOfTurnsCompleted;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public int getNumberOfTurnsCompleted() {
        return numberOfTurnsCompleted;
    }

    public void setNumberOfTurnsCompleted(int numberOfTurnsCompleted) {
        this.numberOfTurnsCompleted = numberOfTurnsCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
