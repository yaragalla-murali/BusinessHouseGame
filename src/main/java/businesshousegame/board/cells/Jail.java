package businesshousegame.board.cells;

import businesshousegame.Player;

public class Jail implements Cell {

	private final int penalty = 150;

	@Override
	public int handleCellLanding(Player player) {
		int balanceAmt = player.getBalanceAmt() - penalty;
		return balanceAmt;
	}

}
