package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import businesshousegame.Player;

public class PlayerTest {

	@Test
	public void playerMove() {
		Player player = new Player("hari");
		int diceOutput = 3;
		int boardMaxCells = 10;
		int currentPositionOnBoard = player.move(diceOutput, boardMaxCells);
		assertEquals(3, currentPositionOnBoard);
	}

	@Test
	public void playerMoveWhenExceededBoardMaxCells() {
		Player player = new Player("hari");
		player.setCurrentPositionOnBoard(6);
		int diceOutput = 6;
		int boardMaxCells = 10;
		int currentPositionOnBoard = player.move(diceOutput, boardMaxCells);
		assertEquals(2, currentPositionOnBoard);
	}
}
