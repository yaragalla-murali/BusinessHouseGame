package businesshousegame.board.cells;

import businesshousegame.Player;

public class Treasure implements Cell {
	
	private final int treasureValue=200;

	
	@Override
	public Player handleLandMoney(Player player) {
		int currentBalance=player.getBalanceAmount()+treasureValue;
		player.setBalanceAmount(currentBalance);
		return player;
	}
}
