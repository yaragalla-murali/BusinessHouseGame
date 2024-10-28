package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Jail;

public class JailTest {

	@Test
	public void handleJailCellTest() {
		Cell jail = new Jail();
		Player player = Mockito.mock(Player.class);
		when(player.getBalanceAmt()).thenReturn(1000);
		int balanceAmt = jail.handleCellLanding(player);
		assertEquals(850, balanceAmt);
	}
}
