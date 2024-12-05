package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

@Entity
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
   // @Column(name = "cell_type")
    private CellType cellType;    
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 150")
    private int jailPenalty;    
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 200")
    private int treasureValue;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 200")
    private int hotelWorth;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 50")
    private int hotelRent;
    @OneToOne
    private Player hotelOwner;
    private int sequenceOnBoard;

    public Cell() {
    }

    public Cell(CellType cellType, int counter) {
        this.cellType = cellType;
        this.sequenceOnBoard = counter;
    }

    public Cell(int id, CellType cellType) {
        this.id = id;
        this.cellType = cellType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getJailPenalty() {
        return jailPenalty;
    }

    public void setJailPenalty(int jailPenalty) {
        this.jailPenalty = jailPenalty;
    }

    public int getTreasureValue() {
        return treasureValue;
    }

    public void setTreasureValue(int treasureValue) {
        this.treasureValue = treasureValue;
    }

    public int getHotelWorth() {
        return hotelWorth;
    }

    public void setHotelWorth(int hotelWorth) {
        this.hotelWorth = hotelWorth;
    }

    public int getHotelRent() {
        return hotelRent;
    }

    public void setHotelRent(int hotelRent) {
        this.hotelRent = hotelRent;
    }

    public Player getHotelOwner() {
        return hotelOwner;
    }

    public void setHotelOwner(Player hotelOwner) {
        this.hotelOwner = hotelOwner;
    }

    public int getSequenceOnBoard() {
        return sequenceOnBoard;
    }

    public void setSequenceOnBoard(int sequenceOnBoard) {
        this.sequenceOnBoard = sequenceOnBoard;
    }
}
