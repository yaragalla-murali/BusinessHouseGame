package businesshousegame;

import java.util.Arrays;
import java.util.List;

import businesshousegame.board.cells.Cell;

public class BusinessHouseGame {

	public static void main(String[] args) {
		Board board = new Board(
				"E,E,J,H,E,T,J,T,E,E,H,J,T,H,E,E,J,H,E,T,J,T,E,E,H,J,T,H,J,E,E,J,H,E,T,J,T,E,E,H,J,T,E,H,E");
		Dice dice = new Dice("4,4,4,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12,2,3,5,6,7,8,5,11,10,12");
		List<Player> players = Arrays.asList(new Player("Ramu"), new Player("Hari"), new Player("Bharath"));
		Game game = new Game();
		game.playGame(players, dice, board);

	}

}
