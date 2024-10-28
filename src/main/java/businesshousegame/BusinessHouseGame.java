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
		BusinessHouseGame bhg = new BusinessHouseGame();
		bhg.playGame(players, dice, board);

	}

	public List<Player> playGame(List<Player> players, Dice dice, Board board) {
		for (int set = 0; set < 10; set++) {
			System.out.println("The number of set is " + (set + 1));
			playOneTurn(players, dice, board);
		}
		System.out.println("----------------------------Final Results-------------------");
		players.forEach(player -> System.out.println(player.getName() + " has worth of " + player.getBalanceAmt()));
		return players;
	}

	public List<Player> playOneTurn(List<Player> players, Dice dice, Board board) {

		players.forEach(player -> {
			int diceOutput = dice.getDice();
			int currentPositionOnBoard = player.move(diceOutput, board.getGrid().size());
			Cell currentCellOnBoard = board.getGrid().get(currentPositionOnBoard);
			if (currentCellOnBoard != null) {
				int balanceAmount = currentCellOnBoard.handleCellLanding(player);
				player.setBalanceAmt(balanceAmount);
			}
			System.out.println(player.getName() + " current Position on board is " + currentPositionOnBoard
					+ " and has balance of " + player.getBalanceAmt());

		});

		return players;

	}

}
