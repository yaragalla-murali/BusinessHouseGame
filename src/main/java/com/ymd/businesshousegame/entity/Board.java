package com.ymd.businesshousegame.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("sequenceOnBoard")
	private List<Cell> cells;

	public Board() {
	}

	public Board(List<Cell> cells) {
		this.cells = cells;
	}

	public Board(int id, List<Cell> cells) {
		this.id = id;
		this.cells = cells;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

}
