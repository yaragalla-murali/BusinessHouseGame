package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {

    @Column(name = "board_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @OrderBy("sequenceOnBoard")
    private List<Cell> boardCells;

    public Board() {
    }

    public Board(List<Cell> boardCells) {
        this.boardCells = boardCells;
    }

    public Board(int id, List<Cell> boardCells) {
        this.id = id;
        this.boardCells = boardCells;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cell> getBoardCells() {
        return boardCells;
    }

    public void setBoardCells(List<Cell> boardCells) {
        this.boardCells = boardCells;
    }

}
