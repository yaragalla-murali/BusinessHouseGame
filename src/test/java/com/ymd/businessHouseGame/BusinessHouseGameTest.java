package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import businesshousegame.Board;
import businesshousegame.BusinessHouseGame;
import businesshousegame.Dice;
import businesshousegame.Player;

public class BusinessHouseGameTest {

	@Test
	public void businessHouseGame() {
		Board board = new Board("J,T,H,E");
		Dice dice = new Dice("1,2");
		List<Player> players = Arrays.asList(new Player("Hari"));
		BusinessHouseGame bhg = new BusinessHouseGame();
		List<Player> playersAfterOneTurn = bhg.playOneTurn(players, dice, board);
		Player playerAfterOneTurn=playersAfterOneTurn.getFirst();
		assertTrue(playerAfterOneTurn.getBalanceAmt()==1200 && playerAfterOneTurn.getCurrentPositionOnBoard()==1);
	}

}
