package businesshousegame.board.cells;

import businesshousegame.Player;

public class Jail implements Cell {
	
	private final int penality=150;
	

	@Override
	public Player handleLandMoney(Player player) {
		int playerBalanceAmt=player.getBalanceAmount()-penality;
		player.setBalanceAmount(playerBalanceAmt);
		return player;
	}
}
