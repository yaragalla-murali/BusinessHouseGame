package businesshousegame.board.cells;

import businesshousegame.Player;

public class Hotel implements Cell {
	
	private final int worth=200;
	private final int rent=50;
	private Player owner;	

	@Override
	public Player handleLandMoney(Player player) {
		if(owner==null) {
			owner=player;
			int balanceAmt=player.getBalanceAmount()-worth;
			player.setBalanceAmount(balanceAmt);			
		}else {
			int balanceAmt=player.getBalanceAmount()-rent;
			player.setBalanceAmount(balanceAmt);
		}
		return player;
	}
}
