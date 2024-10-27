package businesshousegame.board.cells;

import businesshousegame.Player;

public class Hotel implements Cell {

	private final int hotelWorth = 200;
	private final int rent = 50;
	private Player owner;

	@Override
	public int handleCellLanding(Player player) {
		int balanceAmt = 0;
		if (owner == null) {
			balanceAmt = purchaseHotel(player);
		} else {
			balanceAmt = rentHotel(player);
		}
		return balanceAmt;
	}

	private int purchaseHotel(Player player) {
		owner = player;
		int balanceAmt = player.getBalanceAmt() - hotelWorth;
		return balanceAmt;
	}

	private int rentHotel(Player player) {
		int balanceAmt = player.getBalanceAmt() - rent;
		return balanceAmt;
	}

}
