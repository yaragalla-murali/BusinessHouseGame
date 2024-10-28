package com.ymd.businessHouseGame;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import businesshousegame.Board;
import businesshousegame.Dice;
import businesshousegame.Game;
import businesshousegame.Player;

public class GameTest {

	@Test
	public void gameSingleTurn() {
		Board board = new Board("J,T,H,E");
		Dice dice = new Dice("1,2");
		List<Player> players = Arrays.asList(new Player("Hari"));
		Game game = new Game();
		List<Player> playersAfterOneTurn = game.playOneTurn(players, dice, board);
		Player playerAfterOneTurn = playersAfterOneTurn.getFirst();
		assertTrue(playerAfterOneTurn.getBalanceAmt() == 1200 && playerAfterOneTurn.getCurrentPositionOnBoard() == 1);
	}

	@Test
	public void playGame() {
		Board board = new Board("J,T,H,E,J,T,H,E,J,T,H,E");
		Dice dice = new Dice("1,2,1,2,1,2,1,2,1,2");
		List<Player> players = Arrays.asList(new Player("Hari"));
		Game game = new Game();
		List<Player> playersAfterGame = game.playGame(players, dice, board);
		Player playerAfterGame = playersAfterGame.getFirst();
		assertTrue(playerAfterGame.getBalanceAmt() == 900 && playerAfterGame.getCurrentPositionOnBoard() == 3);
	}

}
