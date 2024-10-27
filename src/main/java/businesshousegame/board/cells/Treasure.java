package businesshousegame.board.cells;

import businesshousegame.Player;

public class Treasure implements Cell {

	private final int treasureValue = 200;

	@Override
	public int handleCellLanding(Player player) {
		int balanceAmt = player.getBalanceAmt() + treasureValue;
		return balanceAmt;
	}

}
