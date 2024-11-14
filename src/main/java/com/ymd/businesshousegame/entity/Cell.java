package com.ymd.businesshousegame.entity;

import com.ymd.businesshousegame.model.CellType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Enumerated(EnumType.STRING)
	@Column(name = "cell_type")
	private CellType cellType;
	private int jailPenalty = 150;
	private int treasureValue = 200;
	private int hotelWorth = 200;
	private int hotelRent = 50;
	@OneToOne
	private Player hotelOwner;
	private int sequenceOnBoard;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
