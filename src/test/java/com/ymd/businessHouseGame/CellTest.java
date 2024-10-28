package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;

public class CellTest {

	@Test
	public void handleCellLanding() {
		Cell cell = new Cell() {
			@Override
			public int handleCellLanding(Player player) {
				// TODO Auto-generated method stub
				return Cell.super.handleCellLanding(player);
			}
		};

		Player player = Mockito.mock(Player.class);
		int currentBalance = cell.handleCellLanding(player);
		assertEquals(0,currentBalance);
	}
}
