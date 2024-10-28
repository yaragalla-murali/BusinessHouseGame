package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import businesshousegame.Board;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Hotel;
import businesshousegame.board.cells.Jail;
import businesshousegame.board.cells.Treasure;

public class BoardTest {

	@Test
	public void boardSetup() {
		Board board = new Board("J,T,H,E");
		List<Cell> grid = board.getGrid();
		assertTrue(grid.get(0) instanceof Jail && grid.get(1) instanceof Treasure && grid.get(2) instanceof Hotel
				&& grid.get(3) == null);
	}
}
