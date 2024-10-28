package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Treasure;

public class TreasureTest {

	@Test
	public void handleTreasureCell() {
		Cell treasure = new Treasure();
		Player player = Mockito.mock(Player.class);
		when(player.getBalanceAmt()).thenReturn(1000);
		int balanceAmt = treasure.handleCellLanding(player);
		assertEquals(1200, balanceAmt);
	}
}
