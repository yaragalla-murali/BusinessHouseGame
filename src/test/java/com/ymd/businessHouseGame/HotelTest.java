package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import businesshousegame.Player;
import businesshousegame.board.cells.Cell;
import businesshousegame.board.cells.Hotel;

public class HotelTest {

	@Test
	public void handleOwningHotel() {
		Cell hotel = new Hotel();
		Player player = Mockito.mock(Player.class);
		when(player.getBalanceAmt()).thenReturn(1000);
		int balanceAmt = hotel.handleCellLanding(player);
		assertEquals(800, balanceAmt);
	}

	@Test
	public void handleRentingHotel() {
		Hotel hotel = new Hotel();
		Player player = Mockito.mock(Player.class);
		when(player.getBalanceAmt()).thenReturn(1000);
		hotel.setOwner(player);
		int balanceAmt = hotel.handleCellLanding(player);
		assertEquals(950, balanceAmt);
	}

	@Test
	public void getOwner() {
		Hotel hotel = new Hotel();
		Player player = Mockito.mock(Player.class);
		hotel.setOwner(player);
		Player owner = hotel.getOwner();
		assertNotNull(owner);
	}
}
